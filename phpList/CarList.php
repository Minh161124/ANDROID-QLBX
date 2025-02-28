<?php

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "qlbxmd";

// Kết nối tới database
$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die(json_encode(["error" => "Connection failed: " . $conn->connect_error]));
}

// Lấy dữ liệu từ bảng cars
$sql = "SELECT id, bienso, tenxe, tentaixe, sdt, phi, tuyen FROM cars";
$result = $conn->query($sql);

$data = [];

if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $data[] = $row;
    }
    echo json_encode($data);
} else {
    echo json_encode([]);
}

$conn->close();
?>