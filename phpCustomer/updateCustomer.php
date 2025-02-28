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


$makh = $data->makh;
$tenkh = $data->tenkh;
$madv = $data->madv;
$mave = $data->mave;
$tuoi = $data->tuoi;
$gioitinh = $data->gioitinh; // Nam/Nữ
$sdt = $data->sdt;
$giave = $data->giave;
$tuyen = $data->tuyen;


$sql = "UPDATE customers SET tenkh='$tenkh', tuoi='$tuoi', gioitinh='$gioitinh', sdt='$sdt', giave='$giave', tuyen='$tuyen', madv='$madv', mave='$mave' WHERE makh='$makh'";


if ($conn->query($sql) === TRUE) {
    echo json_encode(["message" => "Cập nhật nhân viên thành công"]);
} else {
    echo json_encode(["error" => "Lỗi: " . $conn->error]);
}


$conn->close();
?>
