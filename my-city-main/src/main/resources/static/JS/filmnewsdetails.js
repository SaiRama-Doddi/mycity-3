
import { detailedNews } from './../data/js/filmNewsDetail.js';
import filmnews from './../data/js/filmnews.js';


function loadFilmNewsDetails() {
    function getQueryParams() {
        const params = new URLSearchParams(window.location.search);
        const ids = ['1', '2', '3', '4',]

        return ids.includes(params.get('id')) ? params.get('id') : 0;

    }

    const id = getQueryParams();

    const currentNews = detailedNews.latest[id];

    const mainContent = document.getElementById('main-content');
    const sideContent = document.getElementById('side-content');

    if (currentNews.title) {
        mainContent.innerHTML += `<h1 class='news-title text-[#F37022] font-bold text-[22px]'>${currentNews.title}</h1>`;
    }

    if (currentNews.p1) {
        mainContent.innerHTML += `<div class='first-para flex gap-[1rem] mt-[4rem] items-center'><p>${currentNews.p1}</p><img src="../src/assets/images/filmnews/${currentNews.image}" alt="${currentNews.h1}" class='w-[331px] h-[176px]' /></div>`;
       
    }

    const detailsContent = `
        <div class='details-content mt-[2rem] flex flex-col gap-[2rem]'>
            ${currentNews.p2 ? `<p>${currentNews.p2}</p>` : ""}
            ${currentNews.p3 ? `<p>${currentNews.p3}</p>` : ""}
            ${currentNews.h1 ? `<h2 class='text-[24px] font-semibold text-[#F37022]'>${currentNews.h1}</h2>` : ""}
            ${currentNews.p4 ? `<p>${currentNews.p4}</p>` : ""}
            ${currentNews.h2 ? `<h2 class='text-[24px] font-semibold text-[#F37022]'>${currentNews.h2}</h2>` : ""}
            ${currentNews.p4 ? `<p>${currentNews.p4}</p>` : ""}
        </div>`;
    mainContent.innerHTML += detailsContent;

    // Side content
    const sideNewsContent = `
        <div class='news-letter mt-[1rem]'>
            <div class="font-semibold text-white text-[32px] mb-[1.5rem]">Subscribe For Newsletter</div>
            <form action="" class='flex flex-col gap-[1.5rem]'>
                <input type='email' name="email" id="email" placeholder='Enter Your Email Address' class='text-black outline-none focus:ring focus:ring-[gray] font-medium placeholder:text-[#666666] w-[483px] h-[98px] px-[2rem] content-center rounded-[15px] placeholder:text-[24px]' />
                <div class='w-[205px] h-[73px] text-center content-center text-[#F37022] subscribebtn bg-transparent text-[25px] font-bold border-[3px] border-[#F37022] rounded-xl hover:scale-[1.05] cursor-pointer '>
                    <p class='[text-shadow:_1px_4px_4px_rgb(0_0_0_/_40%)] text-[30px]'>Submit</p>
                </div>
            </form>
        </div>
        <div class='sidenews flex gap-[2rem] flex-col bg-[#202020DB] w-[483px] h-[660px] rounded-[15px] px-2 py-6 mt-[2rem]'>
            <div class='flex gap-2 items-center h-[30px]'>
                <img src="../src/assets/icons/fire2.svg" alt="Fire icon" class='w-[40px] h-[40px]' />
                <h1 class='title1 latest-title font-semibold'>Latest News</h1>
                <h1 class='title1 interview-title font-semibold hidden'>Interview</h1>
            </div>
            <div class='news-content flex flex-col items-center mr-[2rem] overflow-y-scroll scrollbarhide gap-[2rem] justify-between pl-[30px]'>
                ${filmnews.latest.map((news, index) => `
                    <div class="news flex flex-col h-[292px]" key=${index}>
                        <img src="../src/assets/images/filmnews/${news.image}.png" alt="${news.image}" class='w-[290px] object-contain h-[164px]' />
                        <h3 class='text-[13px] font-semibold w-[220px] mt-2 mb-2'>${news.news}</h3>
                        <a href="../src/newsDetails.html?id=${index}">
                            <p class='mt-auto bg-[#F37022] w-[100px] h-[29px] content-center text-center rounded-[20px] text-[11px] font-bold'>Read more</p>
                        </a>
                    </div>
                `).join('')}
            </div>
        </div>`;
    sideContent.innerHTML += sideNewsContent;
}

// Load details when the page is ready
document.addEventListener('DOMContentLoaded', loadFilmNewsDetails);