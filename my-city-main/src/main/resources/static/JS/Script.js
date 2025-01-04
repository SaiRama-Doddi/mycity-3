const greetingElement=document.getElementById("greeting");
const currentHour=new Date().getHours();

let greetingText;
if(currentHour<12){
    greetingText="Good Morning";
    } else if(currentHour<18){
        greetingText="Good Afternoon";
    } else {
        greetingText="Good Evening";
    }

greetingElement.textContent=`${greetingText}`;