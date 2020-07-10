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
