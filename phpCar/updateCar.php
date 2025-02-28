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
$tenxe = $data->tenxe;
$tentaixe = $data->tentaixe;
$tuoi = $data->tuoi;
$gioitinh = $data->gioitinh; // Nam/Nữ
$sdt = $data->sdt;
$phi = $data->phi;
$tuyen = $data->tuyen;
$ghengoi = $data->ghengoi;
$mdv = $data->mdv;


$sql = "UPDATE cars SET tenxe='$tenxe', tentaixe='$tentaixe', tuoi='$tuoi', gioitinh='$gioitinh', sdt='$sdt', phi='$phi', tuyen='$tuyen', ghengoi='$ghengoi', mdv='$mdv' WHERE bienso=$bienso";

if ($conn->query($sql) === TRUE) {
    echo json_encode(["message" => "Cap nhat thong tin thanh cong"]);
} else {
    echo json_encode(["error" => "Lỗi: " . $conn->error]);
}


$conn->close();
?>
