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
if (!isset($data->makh, $data->tenkh, $data->madv, $data->mave, $data->tuoi, $data->gioitinh, $data->sdt, $data->giave, $data->tuyen)) {
    echo json_encode(["error" => "Invalid input data"]);
    exit();
}

// Gán giá trị từ JSON vào các biến PHP
$makh = $data->makh;
$tenkh = $data->tenkh;
$madv = $data->madv;
$mave = $data->mave;
$tuoi = $data->tuoi;
$gioitinh = $data->gioitinh; // Nam/Nữ
$sdt = $data->sdt;
$giave = $data->giave;
$tuyen = $data->tuyen;


// Chuẩn bị câu lệnh SQL
$stmt = $conn->prepare("INSERT INTO customers (makh, tenkh, madv, mave, tuoi, gioitinh, sdt, giave, tuyen) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
$stmt->bind_param("ssssissis", $makh, $tenkh, $madv, $mave, $tuoi, $gioitinh, $sdt, $giave, $tuyen);

// Thực thi câu lệnh SQL và kiểm tra kết quả
if ($stmt->execute()) {
    echo json_encode(["message" => "Add customer Successfully"]);
} else {
    echo json_encode(["error" => "Lỗi: " . $stmt->error]);
}

// Đóng kết nối
$stmt->close();
$conn->close();
?>

