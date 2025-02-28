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
if (!isset($data->mve, $data->mkh, $data->tenkh, $data->tuoikh, $data->gtkh, $data->tennv, $data->tuoinv, $data->gtnv, $data->number, $data->giave, $data->route, $data->seat, $data->madv)) {
    echo json_encode(["error" => "Invalid input data"]);
    exit();
}


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


$stmt = $conn->prepare("INSERT INTO tickets (mve, mkh, tenkh, tuoikh, gtkh, tennv, tuoinv, gtnv, number, giave, route, seat, madv) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
$stmt->bind_param("ississisisisi", $mve, $mkh, $tenkh, $tuoikh, $gtkh, $tennv, $tuoinv, $gtnv, $number, $giave, $route, $seat, $madv);


if ($stmt->execute()) {
    echo json_encode(["message" => "Add ticket Successfully"]);
} else {
    echo json_encode(["error" => "Lỗi: " . $stmt->error]);
}

// Đóng kết nối
$stmt->close();
$conn->close();

?>

