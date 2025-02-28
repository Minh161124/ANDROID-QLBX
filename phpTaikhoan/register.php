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

if (!isset($data->username, $data->phone, $data->gioitinh, $data->password)) {
    echo json_encode(["error" => "Invalid input data"]);
    exit();
}

$username = $data->username;
$phone = $data->phone;
$gioitinh = $data->gioitinh;
$password = $data->password; 

$stmt = $conn->prepare("SELECT id FROM users WHERE username = ?");
$stmt->bind_param("s", $username);
$stmt->execute();
$stmt->store_result();

if ($stmt->num_rows > 0) {
    echo json_encode(["error" => "Username đã tồn tại"]);
} else {
    $stmt = $conn->prepare("INSERT INTO userCus (username, phone, gioitinh, password) VALUES (?, ?, ?, ?)");
    $stmt->bind_param("siss", $username, $phone, $gioitinh, $password); 

    if ($stmt->execute()) {
        echo json_encode(["message" => "Đăng ký thành công"]);
    } else {
        echo json_encode(["error" => "Lỗi: " . $stmt->error]);
    }
}

$stmt->close();
$conn->close();
?>
