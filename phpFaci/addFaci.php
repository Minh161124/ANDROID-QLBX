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
if (!isset($data->mavt, $data->tenvt, $data->soluong, $data->hsd, $data->gia)) {
    echo json_encode(["error" => "Invalid input data"]);
    exit();
}

// Gán giá trị từ JSON vào các biến PHP
$mavt = $data->mavt;
$tenvt = $data->tenvt;
$soluong = $data->soluong;
$hsd = $data->hsd;
$gia = $data->gia; 


// Chuẩn bị câu lệnh SQL
$stmt = $conn->prepare("INSERT INTO facilities (mavt, tenvt, soluong, hsd, gia) VALUES (?, ?, ?, ?, ?)");
$stmt->bind_param("isisi", $mavt, $tenvt, $soluong, $hsd, $gia);

// Thực thi câu lệnh SQL và kiểm tra kết quả
if ($stmt->execute()) {
    echo json_encode(["message" => "Add facilitie Successfully"]);
} else {
    echo json_encode(["error" => "Lỗi: " . $stmt->error]);
}

// Đóng kết nối
$stmt->close();
$conn->close();
?>
