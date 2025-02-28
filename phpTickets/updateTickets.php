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


$mve = $data->mve;
$mkh = $data->mkh;
$tenkh = $data->tenkh;
$tuoikh = $data->tuoikh;
$gtkh = $data->gtkh;
$tennv = $data->tennv;
$tuoinv = $data->tuoinv;
$gtnv = $data->gtnv;
$number = $data->number;
$giave = $data->giave;
$route = $data->route;
$seat = $data->seat;
$madv = $data->madv;

$sql = "UPDATE tickets SET 
    mkh='$mkh', 
    tenkh='$tenkh', 
    tuoikh='$tuoikh', 
    gtkh='$gtkh', 
    tennv='$tennv', 
    tuoinv='$tuoinv', 
    gtnv='$gtnv', 
    number='$number', 
    giave='$giave', 
    route='$route', 
    seat='$seat', 
    madv='$madv' 
WHERE mve='$mve'";


if ($conn->query($sql) === TRUE) {
    echo json_encode(["message" => "Cập nhật nhân viên thành công"]);
} else {
    echo json_encode(["error" => "Lỗi: " . $conn->error]);
}


$conn->close();
?>
