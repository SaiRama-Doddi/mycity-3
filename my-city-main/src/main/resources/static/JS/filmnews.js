
import filmnews from './../data/js/filmnews.js';
  const socialmedia = ['instagram','facebook','googleplus','x'];
  
  const latestNewsContainer = document.getElementById('latestNewsContainer');
  filmnews.latest.forEach((news, index) => {
    const newsDiv = document.createElement('div');
    newsDiv.className = 'news flex flex-col h-[280px]';
    newsDiv.innerHTML = `
      <img src="../src/assets/images/filmnews/${news.image}.png" alt="${news.image}" class="w-[290px] object-contain h-[164px]" />
      <h3 class="text-[13px] font-semibold w-[220px] mt-2">${news.news}</h3>
      <a href="../src/newsDetails.html?id=${index}" class="mt-auto">
        <p class="px-[15px] py-[9px] bg-[#F37022] w-fit rounded-[20px] text-[11px] font-semibold">Read more</p>
      </a>
    `;
    latestNewsContainer.appendChild(newsDiv);
  });
  
  
  const interviewsContainer = document.getElementById('interviewsContainer');
  filmnews.interviews.forEach((interview, index) => {
    const interviewLink = document.createElement('a');
    interviewLink.href = interview.link;
    const interviewDiv = document.createElement('div');
    interviewDiv.className = 'flex report flex-col h-[164px] w-[290px]';
    interviewDiv.innerHTML = `
      <img src="../src/assets/images/filmnews/${interview.image}.png" alt="${interview.image}" class="w-[290px] h-[164px] object-contain" />
      <h3 class="text-[20px] font-bold w-[290px] mt-2">${interview.name}</h3>
      <p class="text-[13px] text-[#666666] font-bold">${interview.reporter}</p>
    `;
    interviewLink.appendChild(interviewDiv)
    interviewsContainer.appendChild(interviewLink);
  });
  
  
  const socialMediaContainer = document.getElementById('socialMediaContainer');
  socialmedia.forEach((social, index) => {
    const socialLink = document.createElement('a');
    socialLink.href = '#';
    socialLink.innerHTML = `<img src="../src/assets/icons/${social}.svg" alt="${social}" class="w-[50px] text-white" />`;
    socialMediaContainer.appendChild(socialLink);
  });
  
  
  document.getElementById('newsletterForm').addEventListener('submit', function (e) {
    e.preventDefault();
    const emailInput = document.getElementById('mail');
    if (emailInput.value) {
      alert(`Email submitted: ${emailInput.value}`);
      emailInput.value = ''; 
    }
  });
  