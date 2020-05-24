var passwordPairs = {
  "sarahford": "password",
  "sford85": "password2"
}

function check(form) {
  if (passwordPairs[form.userid.value] === form.pswrd.value)
  { 
    window.open('target.html');
    alert("success");
    return false;
  }
}

$('a.registerbtn').click(function() {
  $(this).parents('.flip-card-inner').css('transform', 'rotateY(180deg)');
});

$("#unflip-btn").click(function(){
  
  $(this).parents('.flip-card-inner').css('transform', 'rotateY(0deg)');
	
});