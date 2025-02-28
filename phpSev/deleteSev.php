<?php

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "qlbxmd";

$conn = new mysqli($servername, $username, $password, $dbname);


if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}


$data = json_decode(file_get_contents("php://input"));


$madv = $data->madv;


$sql = "DELETE FROM service WHERE madv=$madv";

if ($conn->query($sql) === TRUE) {
    echo json_encode(["message" => "Xoa dich vu thanh cong"]);
} else {
    echo json_encode(["error" => "Lỗi: " . $conn->error]);
}


$conn->close();
?>

