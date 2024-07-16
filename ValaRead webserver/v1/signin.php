<?php
header('Content-Type: application/json');

try {
    require_once '../includes/DBConnect.php';
    $db = new DBConnect();
    $conn = $db->connect();

    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    $email = filter_input(INPUT_POST, 'email', FILTER_VALIDATE_EMAIL);
    $password = $_POST['password'];

    if (!$email || !$password) {
        echo json_encode(["status" => "error", "message" => "Invalid input."]);
        exit;
    }

    $stmt = $conn->prepare("SELECT * FROM users WHERE email = :email");
    $stmt->bindParam(':email', $email);
    $stmt->execute();

    if ($stmt->rowCount() > 0) {
        $user = $stmt->fetch(PDO::FETCH_ASSOC);
        
        if (password_verify($password, $user['password_hash'])) {
            echo json_encode([
                "status" => "success",
                "message" => "Login successful.",
                "user" => [
                    "first_name" => htmlspecialchars($user['first_name']),
                    "last_name" => htmlspecialchars($user['last_name']),
                    "email" => htmlspecialchars($user['email'])
                ]
            ]);
        } else {
            echo json_encode(["status" => "error", "message" => "Login failed. Incorrect password."]);
        }
    } else {
        echo json_encode(["status" => "error", "message" => "Login failed. User not found."]);
    }
} catch (PDOException $e) {
    echo json_encode(["status" => "error", "message" => "Error: " . htmlspecialchars($e->getMessage())]);
}

$conn = null;
?>
