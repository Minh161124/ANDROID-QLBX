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


$mavt = $data->mavt;


$sql = "DELETE FROM facilities WHERE mavt=$mavt";

if ($conn->query($sql) === TRUE) {
    echo json_encode(["message" => "Xoa vat tu thanh cong"]);
} else {
    echo json_encode(["error" => "Lỗi: " . $conn->error]);
}


$conn->close();
?>

