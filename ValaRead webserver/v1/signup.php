<?php
try {
    require_once '../includes/DBConnect.php';
        $db= new DBConnect();
        $conn=$db->connect();
    
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    $email = filter_var($_POST['email'], FILTER_SANITIZE_EMAIL);
    $firstName = $_POST['first_name'];
    $lastName = $_POST['last_name'];
    $password = $_POST['password'];

    if (empty($email) || empty($firstName) || empty($lastName) || empty($password)) {
        echo json_encode(["error" => "Please fill in all fields"]);
        exit;
    }

    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        echo json_encode(["error" => "Invalid email format"]);
        exit;
    }

    $stmt_check_email = $conn->prepare("SELECT COUNT(*) FROM users WHERE email = :email");
    $stmt_check_email->bindParam(':email', $email);
    $stmt_check_email->execute();
    $email_count = $stmt_check_email->fetchColumn();

    if ($email_count > 0) {
        echo json_encode(["error" => "Email already exists"]);
        exit;
    }

    $passwordHash = password_hash($password, PASSWORD_DEFAULT);

    $stmt = $conn->prepare("INSERT INTO users (email, first_name, last_name, password_hash) 
                            VALUES (:email, :firstName, :lastName, :passwordHash)");


    $stmt->bindParam(':email', $email);
    $stmt->bindParam(':firstName', $firstName);
    $stmt->bindParam(':lastName', $lastName);
    $stmt->bindParam(':passwordHash', $passwordHash);

    // Execute the prepared statement
    $stmt->execute();

    echo json_encode(["message" => "Sign Up successful"]);
} catch(PDOException $e) {
    echo json_encode(["error" => "Error: " . $e->getMessage()]);
} finally {
    // Close the connection
    $conn = null;
}
?>
