<?php

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "qlbxmd";

// Kết nối tới database
$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    echo json_encode(["error" => "Connection failed: " . $conn->connect_error]);
    exit();
}

// Lấy dữ liệu từ request JSON
$data = json_decode(file_get_contents("php://input"));

// Kiểm tra dữ liệu đầu vào
if (!isset($data->manv, $data->ten, $data->tuoi, $data->gioitinh, $data->sdt, $data->luong, $data->chucvu)) {
    echo json_encode(["error" => "Invalid input data"]);
    exit();
}

// Gán giá trị từ JSON vào các biến PHP
$manv = $data->manv;
$ten = $data->ten;
$tuoi = $data->tuoi;
$gioitinh = $data->gioitinh; // Nam/Nữ
$sdt = $data->sdt;
$luong = $data->luong;
$chucvu = $data->chucvu;

// Chuẩn bị câu lệnh SQL
$stmt = $conn->prepare("INSERT INTO employee (manv, ten, tuoi, gioitinh, sdt, luong, chucvu) VALUES (?, ?, ?, ?, ?, ?, ?)");
$stmt->bind_param("ssissis", $manv, $ten, $tuoi, $gioitinh, $sdt, $luong, $chucvu);

// Thực thi câu lệnh SQL và kiểm tra kết quả
if ($stmt->execute()) {
    echo json_encode(["message" => "Add employee Successfully"]);
} else {
    echo json_encode(["error" => "Lỗi: " . $stmt->error]);
}

// Đóng kết nối
$stmt->close();
$conn->close();
?>

