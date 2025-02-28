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


$bienso = $data->bienso;


$sql = "DELETE FROM cars WHERE bienso=$bienso";

if ($conn->query($sql) === TRUE) {
    echo json_encode(["message" => "Xoa thanh cong"]);
} else {
    echo json_encode(["error" => "Lỗi: " . $conn->error]);
}


$conn->close();
?>