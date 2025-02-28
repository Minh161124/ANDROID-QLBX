<?php

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "qlbxmd";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    echo json_encode(["error" => "Connection failed: " . $conn->connect_error]);
    exit();
}

$data = json_decode(file_get_contents("php://input"));

if (!isset($data->username, $data->password)) {
    echo json_encode(["error" => "Invalid input data"]);
    exit();
}

$username = $data->username;
$password = $data->password;  

$stmt = $conn->prepare("SELECT password FROM users WHERE username = ?");
$stmt->bind_param("s", $username);
$stmt->execute();
$stmt->bind_result($stored_password);

if ($stmt->fetch()) {
    if ($password === $stored_password) {  
        echo json_encode(["message" => "Dang nhap thanh cong"]);
    } else {
        echo json_encode(["error" => "Mat khau khong dung"]);
    }
} else {
    echo json_encode(["error" => "Username khong ton tai"]);
}

$stmt->close();
$conn->close();
?>
