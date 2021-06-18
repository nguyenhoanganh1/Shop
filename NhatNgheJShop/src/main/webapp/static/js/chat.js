const url = "http://localhost";
let stompClient;
let selectedUser;

function connectToChat(userName){
	console.log("connect to chat");
	let socket = new SockJS(url + '/chat');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame){
		console.log('connect to: ' + frame);
		stompClient.subscribe("/topic/message/" + userName, function(response){
			let data = JSON.parse(response.body);
			render(data.message, data.fromLogin);
		});
	});
	
}

function selectUser(userName){
	console.log("selected user: " + userName)
	selectedUser = userName ;
	$("#selectedUserId").html('');
	$("#selectedUserId").append('Chat with ' + userName);
}

function sendMsg(from, text){
	stompClient.send("/app/chat/" + selectedUser,{}, JSON.stringify({
		fromLogin: from,
		message: text
		}));
}

function registration(){
	let userName = document.getElementById('userName').value;
	$.get(url + "/registration/" + userName, function (response){
		connectToChat(userName);
	}).fail(function(error){
		if(error.status === 400)
		{
			alert("login is aready busy");
		}
	});
}

function fetchAll(){
	$.get(url + "/fetchAllUsers", function (response){
		let users = response;
		let usersTemplateHTML = "";
		
		for(let i = 0; i < users.length; i++)
		{
			usersTemplateHTML = usersTemplateHTML 
			   + '<a href="#" onclick="selectUser(\''+users[i]+'\')"> Choose </a>\n'
			   + '<div class="chat_people">\n'
               + '<div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>\n'
               + '<div class="chat_ib">\n '
               + '<h5 class="name">'+users[i]+' <span class="chat_date">Dec 25</span></h5>\n'  
               + '<p>Test, which is a new approach to have all solutions astrology under one roof.</p>\n' 
               + '</div>\n'
               + '</div>'
		}
		$('#userList').html(usersTemplateHTML);
	});
}


