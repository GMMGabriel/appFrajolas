<?php

$conexao = mysqli_connect('localhost', 'root', 'bcd127', 'dbfrajolas');

$sql = "SELECT * FROM tbl_produto;";

$select = mysqli_query($conexao, $sql);

$lstContatos = array();

while($rsContatos = mysqli_fetch_assoc($select)){
    
    $lstContatos [] = $rsContatos;
    
}

echo json_encode($lstContatos);

?>