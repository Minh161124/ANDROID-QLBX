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


$manv = $data->manv;


$sql = "DELETE FROM employee WHERE manv=$manv";

if ($conn->query($sql) === TRUE) {
    echo json_encode(["message" => "Xoa nhan vien thanh cong"]);
} else {
    echo json_encode(["error" => "Lỗi: " . $conn->error]);
}


$conn->close();
?>

