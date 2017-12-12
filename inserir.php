<?php	

	if ($_SERVER['REQUEST_METHOD'] == 'POST'){
		
		$conexao = mysqli_connect("localhost","root","bcd127", "dbfrajolas");
	
		$nota=$_POST["nota"];
        $idProduto=$_POST['idProduto'];
		
		$sql="CALL sp_avaliacao($nota, $idProduto);";
		
		if (mysqli_query($conexao, $sql)) {
			
			echo json_encode(array(
					"sucesso" => true ,
					"mensagem"=> "Avaliação feita com sucesso"));
		} else {
			
			echo json_encode(array(
					"sucesso" => false ,
					"mensagem" => mysqli_error($conexao)));				
		}
		
	}else{
		
		echo json_encode(array(
		"sucesso" => false ,
		"mensagem"=> "Método não suportado"));		
	}
?>