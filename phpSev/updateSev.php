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
$tendv = $data->tendv;
$giave = $data->giave;

$sql = "UPDATE service SET tendv='$tendv', giave='$giave' WHERE madv='$madv'";

if ($conn->query($sql) === TRUE) {
    echo json_encode(["message" => "Cap nhat dich vu thanh cong"]);
} else {
    echo json_encode(["error" => "Lỗi: " . $conn->error]);
}


$conn->close();
?>
