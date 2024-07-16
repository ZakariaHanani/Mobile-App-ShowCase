<?php
$name = $_POST['name'];
$email = $_POST['email'];
$message = $_POST['message'];

$subject = 'New support message from ' . $name;
$body = "Name: $name\nEmail: $email\nMessage: $message";
$to = 'zakarhanani@gmail.com';

if (mail($to, $subject, $body)) {

    echo "Email sent successfully!";
} else {
    
    echo "Failed to send email.";
}
?>
