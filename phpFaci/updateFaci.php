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
$tenvt = $data->tenvt;
$soluong = $data->soluong;
$hsd = $data->hsd;
$gia = $data->gia; 


$sql = "UPDATE facilities SET tenvt='$tenvt', soluong='$soluong', hsd='$hsd', gia='$gia' WHERE mavt='$mavt'";


if ($conn->query($sql) === TRUE) {
    echo json_encode(["message" => "Cap nhat Vat tu thanh cong"]);
} else {
    echo json_encode(["error" => "Lỗi: " . $conn->error]);
}


$conn->close();
?>
