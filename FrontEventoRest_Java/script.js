var idevento = 0;
listar();
var myModal = new bootstrap.Modal(
  document.getElementById('cadastro')
);


function listar(){
  var requestOptions = {
    method: 'GET',
    redirect: 'follow'
  };

  fetch("http://localhost:8080/EventoRest/EventoRest", requestOptions)
    .then(response => response.json())
    .then(function(result){
      var dados = "<th>Nome</th>";
          dados += "<th>Telefone</th>";
          dados += "<th>E-mail</th>";
          dados += "<th>Local</th>";


      for (const i in result) {

        dados += "<tr>"
            + "<td>" + result[i].nome + "</td>"
            + "<td>" + result[i].telefone + "</td>"
            + "<td>" + result[i].email + "</td>"
            + "<td>" + result[i].localevento + "</td>" 
            + "<td><a class='btn btn-primary' onclick='alterar(" + result[i].idevento + ")'>Alterar</a></td>"
            + "<td><a class='btn btn-danger' onclick='excluir(" + result[i].idevento + ")'>Excluir</a></td>"
            + "</tr>";

      }
      document.getElementById("dados").innerHTML = dados;
    })
    .catch(error => console.log('error', error));
}

function alterar(id){
  idpessoa = id;
  var requestOptions = {
    method: 'GET',
    redirect: 'follow'
  };
   
  fetch("http://localhost:8080/EventoRest/EventoRest/" + idevento, requestOptions)
    .then(response => response.json())
    .then(function(result){
      document.getElementById("nome").value = result.nome;
      document.getElementById("telefone").value = result.telefone;
      document.getElementById("email").value = result.email;
      document.getElementById("localevento").value = result.localevento;
      myModal.show();
    })
    .catch(error => console.log('error', error));
}

function excluir(idevento){
  //alert("excluir " + idevento);
  var raw = "";
 
  var requestOptions = {
    method: 'DELETE',
    body: raw,
    redirect: 'follow'
  };
  fetch("http://localhost:8080/EventoRest/EventoRest/" + idevento, requestOptions)
  .then(response => response.text())
  .then(function(result){
    listar();
  })
  .catch(error => console.log('error', error));
}

function novo(){
  myModal.show();
  idevento=0;
  document.getElementById("nome").value = "";
  document.getElementById("telefone").value = "";
  document.getElementById("email").value = "";
  document.getElementById("localevento").value = "";
}

function salvar(){
  var metodo;
  if (idevento > 0 )  {
    metodo = "PUT";
  }else{
    metodo = "POST";
  }

  var p = {};
  p.idevento = idevento;
  p.nome = document.getElementById("nome").value;
  p.telefone = document.getElementById("telefone").value;
  p.email = document.getElementById("email").value;
  p.localevento = document.getElementById("localevento").value; 
  var raw = JSON.stringify(p);
  console.log(raw);
  if ((p.nome == "") || (p.email == "") || (p.telefone == "")|| (p.localevento == "")){
    alert("nome ?? obrigat??rio");
    return;
  }

  var requestOptions = {
    method: metodo,
    body: raw,
    redirect: 'follow'
  };
 
  fetch("http://localhost:8080/EventoRest/EventoRest", requestOptions)
    .then(response => response.text())
    .then(function (result){
      listar();
    })
    .catch(error => console.log('error', error));

    myModal.hide();
  }


 
