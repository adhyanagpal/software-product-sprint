// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.


const descriptions=[
    "A Node.js project that uses Handlebars as templating engine,"+
    " Sequelize as ORM and PassportJS for user authentication",


    "A mini-project built on the concepts of Database Management Systems, "+
    "using PHP and MySQL. My Role in the Project: Entity-Relationship model designing,"+
    "Schema designing, setting up database constraints and implementing triggers."
]

const links=[
    "https://github.com/adhyanagpal/E-ShoppingProject",
    "https://github.com/adhyanagpal/LIB"
]

function getdescription( btnid ){
    let listid= "p"+btnid;
    let listitem=document.getElementById(listid);

    console.log("innerhtml: "+listitem.innerHTML)

    if(listitem.innerHTML!=""){
        listitem.innerHTML="";
    }
    else{
        listitem.innerHTML="<br> <li>"+ descriptions[parseInt(btnid)-1] +"</li>"+
                        "<br> <li> <a href="+links[parseInt(btnid)-1] + " target='_blank'> View on GitHub </a> </li>"

    }
    
}

const displaycomments = ()=>{
    fetch('/data')
        .then(response=> response.json() )
        .then(messages => {
            const commentslist=document.getElementById('commentslist');
            messages.forEach((msg) => {
                commentslist.appendChild(createComElement(msg));
            })
            
        })

        
}

const createComElement=(msg)=>{
    const com=document.createElement('li');
    const celement=document.createElement('span');
    //celement.innerText=msg.comment;
    let namestring= msg.name=="" ? "Anonymous" :  msg.name;
    namestring+=" ( "+msg.email+" ) ";
    celement.innerText+=namestring+": "+msg.comment;
    const deleteButtonElement = document.createElement('button');
    deleteButtonElement.innerText = 'Delete';
    deleteButtonElement.addEventListener('click', () => {
        
        fetch('/curuserid')
            .then(response=> response.text())
            .then(userid=>{
                // console.log("current user's id: "+userid);
                // console.log("comment's author's id: "+msg.userid);
                // console.log(typeof(userid));
                // console.log(typeof(msg.userid));
                // console.log(userid.trim().valueOf()===msg.userid.trim().valueOf());
                if(userid.trim().valueOf()===""){
                    window.alert("You need to be logged in to be able to delete comments");
                }
                else if(userid.trim().valueOf()===msg.userid.trim().valueOf()){
                    deleteComment(msg);
                    com.remove();
                }
                else{
                    window.alert("You can't delete someone else's comments");
                }
            })
            

            // deleteComment(msg);
            // com.remove();
    })

    com.appendChild(celement);
    com.appendChild(deleteButtonElement);
    return com;

}

function deleteComment(msg) {
  const params = new URLSearchParams();
  params.append('id', msg.id);
  fetch('/delete', 
    {method: 'POST', body: params}
  );
}
const commentsFormDisplay=()=>{
    fetch('/isloggedin')
        .then(response=>response.json())
        .then(loginStatus=>{

            //console.log("return value from login= "+ loginStatus);

            let commentsForm=document.getElementById('comments-form');
            let logindiv=document.getElementById('login');
            let logoutdiv=document.getElementById('logout');

            if(loginStatus.isloggedin===true){
                logoutdiv.innerHTML="<a href=\""+loginStatus.url+"\"> Logout </a>";
                logindiv.hidden=true;
            }
            else{
                commentsForm.hidden=true;
                logoutdiv.hidden=true;
                logindiv.innerHTML="<a href=\""+loginStatus.url+"\"> Login Here to Add or Delete Comments </a>";
                
            }
        })
}


displaycomments();

commentsFormDisplay()