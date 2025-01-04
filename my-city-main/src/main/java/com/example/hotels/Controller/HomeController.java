package com.example.hotels.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.hotels.Model.UserTourism;
import com.example.hotels.Repository.UserTourismRepository;
import com.example.hotels.Service.UserDetails;
import com.example.hotels.Service.UserTourismService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {

    @GetMapping("/mycity-homepage")
    public String Home(HttpSession session, Model model) {
        // String email = (String) session.getAttribute("email");
        // model.addAttribute("email", email);
        UserTourism detailsUser = (UserTourism) session.getAttribute("userDets");

        if (detailsUser != null) {
            model.addAttribute("email", detailsUser.getEmail());
            model.addAttribute("name", detailsUser.getName());
            model.addAttribute("mobile", detailsUser.getMobile());
            model.addAttribute("address", detailsUser.getAddress());

            return "TourismIndex";
        } else {
            return "TourismIndex";
        }
    }

    @GetMapping("/entertainment")
    public String Entertainment() {
        return "Entertainment";
    }

    @GetMapping("/tourism")
    public String Tourism() {
        return "Tourism";
    }

    @GetMapping("signup")
    public String SignUpPage() {
        return "signup";
    }

    @GetMapping("/Tourism/Visakhapatnam/sample")
    public String Sample() {
        return "Tourism/Visakhapatnam/Sample";
    }

    @GetMapping("/Tourism/Overview/sample")
    public String getMethodName() {
        return "Tourism/Overview/Sample";
    }

    /* This is for the Alluri Sitharama Raju */
    @GetMapping("/AlluriSitharamaRaju-Tourism")
    public String AlluriSitharamaRaju() {
        return "AlluriSitharamaRaju-Tourism";
    }

    @GetMapping("/Tourism/AlluriSitharamaRaju/Aaraku")
    public String getAaraku() {
        return "Tourism/AlluriSitharamaRaju/Aaraku";
    }

    @GetMapping("/Tourism/AlluriSitharamaRaju/Borracaves")
    public String getBorracaves() {
        return "Tourism/AlluriSitharamaRaju/Borracaves";
    }

    @GetMapping("/Tourism/AlluriSitharamaRaju/Lambasingi")
    public String getLambasingi() {
        return "/Tourism/AlluriSitharamaRaju/Lambasingi";
    }

    @GetMapping("/Tourism/AlluriSitharamaRaju/ArakuValley")
    public String geArakuValley() {
        return "/Tourism/AlluriSitharamaRaju/ArakuValley";
    }


    @GetMapping("/Tourism/AlluriSitharamaRaju/BorraCaves")
    public String getBorraCaves() {
        return "/Tourism/AlluriSitharamaRaju/BorraCaves";
    }

    @GetMapping("/Tourism/AlluriSitharamaRaju/TYDA")
    public String getTYDA() {
        return "/Tourism/AlluriSitharamaRaju/TYDA";
    }

    @GetMapping("/Tourism/AlluriSitharamaRaju/Ananthagiri")
    public String getAnanthagiri() {
        return "/Tourism/AlluriSitharamaRaju/Ananthagiri";
    }

    @GetMapping("/Tourism/AlluriSitharamaRaju/Paderu")
    public String getPaderu() {
        return "/Tourism/AlluriSitharamaRaju/Paderu";
    }

    @GetMapping("/Tourism/AlluriSitharamaRaju/ModakondammaAmmavariTemple")
    public String getModakondammaAmmavariTemple() {
        return "/Tourism/AlluriSitharamaRaju/ModakondammaAmmavariTemple";
    }

    @GetMapping("/Tourism/AlluriSitharamaRaju/KatikiWaterfalls")
    public String getKatikiWaterfalls() {
        return "/Tourism/AlluriSitharamaRaju/KatikiWaterfalls";
    }

    @GetMapping("/Tourism/AlluriSitharamaRaju/Chintapalli")
    public String getChintapalli() {
        return "/Tourism/AlluriSitharamaRaju/Chintapalli";
    }

    /* This is for the Anakapalle District */
    @GetMapping("/Anakapalle-Tourism")
    public String Anakapalle() {
        return "Anakapalle-Tourism";
    }

    @GetMapping("/Tourism/Anakapalle/BojjanaKonda")
    public String getBojjanaKonda() {
        return "/Tourism/Anakapalle/BojjanaKonda";
    }

    @GetMapping("/Tourism/Anakapalle/LingalaKonda")
    public String getLingalaKonda() {
        return "/Tourism/Anakapalle/LingalaKonda";
    }

    @GetMapping("/Tourism/Anakapalle/KondakarlaAva")
    public String getKondakarlaAva() {
        return "/Tourism/Anakapalle/KondakarlaAva";
    }

    @GetMapping("/Tourism/Anakapalle/Tantadi")
    public String getTantadi() {
        return "/Tourism/Anakapalle/Tantadi";
    }

    @GetMapping("/Tourism/Anakapalle/Pudimadaka")
    public String getPudimadaka() {
        return "/Tourism/Anakapalle/Pudimadaka";
    }

    @GetMapping("/Tourism/Anakapalle/Devipuram")
    public String getDevipuram() {
        return "/Tourism/Anakapalle/Devipuram";
    }

    /* This is for the Ananthapuramu District */
    @GetMapping("/Ananthapuramu-Tourism")
    public String getAnanthapuramu() {
        return "Ananthapuramu-Tourism";
    }

    @GetMapping("/Tourism/Ananthapuramu/BuggaRamalingeswaraSwamiTemple")
    public String getBuggaRamalingeswaraSwamiTemple() {
        return "/Tourism/Ananthapuramu/BuggaRamalingeswaraSwamiTemple";
    }

    @GetMapping("/Tourism/Ananthapuramu/PenukondaFort")
    public String getPenukondaFort() {
        return "/Tourism/Ananthapuramu/PenukondaFort";
    }

    @GetMapping("/Tourism/Ananthapuramu/ThimmammaMarrimanu")
    public String getThimmammaMarrimanu() {
        return "/Tourism/Ananthapuramu/ThimmammaMarrimanu";
    }

    @GetMapping("/Tourism/Ananthapuramu/GootyFort")
    public String getGootyFort() {
        return "/Tourism/Ananthapuramu/GootyFort";
    }

    @GetMapping("/Tourism/Ananthapuramu/HanumanStatue")
    public String getHanumanStatue() {
        return "/Tourism/Ananthapuramu/HanumanStatue";
    }

    @GetMapping("/Tourism/Ananthapuramu/Rayadurg")
    public String getRayadurg() {
        return "/Tourism/Ananthapuramu/Rayadurg";
    }

    @GetMapping("/Tourism/Ananthapuramu/VeeraBhadraTemple")
    public String getVeeraBhadraTemple() {
        return "/Tourism/Ananthapuramu/VeeraBhadraTemple";
    }

    @GetMapping("/Tourism/Ananthapuramu/anantapurclocktower")
    public String getanantapurclocktower() {
        return "/Tourism/Ananthapuramu/anantapurclocktower";
    }

    @GetMapping("/Tourism/Ananthapuramu/Tadipatri")
    public String getTadipatri() {
        return "/Tourism/Ananthapuramu/Tadipatri";
    }

    /* This is for the Chittor District */
    @GetMapping("/Chittor-Tourism")
    public String Chittor() {
        return "Chittor-Tourism";
    }

    @GetMapping("/Tourism/Chittor/Nagari")
    public String getNagari() {
        return "Tourism/Chittor/Nagari";
    }

    @GetMapping("/Tourism/Chittor/Kanipakam")
    public String getKanipakam() {
        return "Tourism/Chittor/Kanipakam";
    }

    @GetMapping("/Tourism/Chittor/Kalavagunta")
    public String getKalavagunta() {
        return "Tourism/Chittor/Kalavagunta";
    }

    @GetMapping("/Tourism/Chittor/Chandragirifort")
    public String getChandra() {
        return "Tourism/Chittor/Chandragirifort";
    }

    @GetMapping("/Tourism/Chittor/Kailasakona")
    public String getkailasakona() {
        return "Tourism/Chittor/Kailasakona";
    }

    @GetMapping("/Bapatla-Tourism")
    public String bapatlaSpot() {
        return "Bapatla-Tourism";
    }

    @GetMapping("/Ntr-Tourism")
    public String ntrSpot() {
        return "Ntr-Tourism";
    }

    @GetMapping("/Tourism/Ntr/Gandhihill")
    public String getGandhihill() {
        return "Tourism/Ntr/Gandhihill";
    }

    @GetMapping("/Tourism/Ntr/Kondapallifort")
    public String getKondapalli() {
        return "Tourism/Ntr/Kondapallifort";
    }

    @GetMapping("/Tourism/Ntr/Yogananda")
    public String getYogananda() {
        return "Tourism/Ntr/Yogananda";
    }

    // GetMapping("/Tourism/")
    // public String getBhavaNarayana() {
    // return "Tourism/Bapatla/Bhavanarayana";
    // }

    @GetMapping("/Tourism/Bapatla/Bhavanarayana")
    public String getBhavanarayana() {
        return "Tourism/Bapatla/Bhavanarayana";
    }

    @GetMapping("/Tourism/Bapatla/Ramapuram")
    public String getRamapuram() {
        return "Tourism/Bapatla/Ramapuram";
    }

    @GetMapping("/Tourism/Bapatla/Singarakonda")
    public String getSingarakonda() {
        return "Tourism/Bapatla/Singarakonda";
    }

    @GetMapping("/Tourism/Bapatla/Suryalankabeach")
    public String getSuryalanka() {
        return "Tourism/Bapatla/Suryalankabeach";
    }

    @GetMapping("/Tourism/Bapatla/Vodarevu")
    public String getVodarevu() {
        return "Tourism/Bapatla/Vodarevu";
    }

    @GetMapping("/Annamayya-Tourism")
    public String annamayyaSpot() {
        return "Annamayya-Tourism";
    }

    @GetMapping("/Tourism/Annamayya/Attirala")
    public String getAttirala() {
        return "Tourism/Annamayya/Attirala";
    }

    @GetMapping("/Tourism/Annamayya/Tarigonda")
    public String getTarikonda() {
        return "Tourism/Annamayya/Tarigonda";
    }

    @GetMapping("/Tourism/Annamayya/Horsely")
    public String getHorsely() {
        return "Tourism/Annamayya/Horsely";
    }

    @GetMapping("/Tourism/Annamayya/Nandalur")
    public String getNandalur() {
        return "Tourism/Annamayya/Nandalur";
    }

    @GetMapping("/Prakasam-Tourism")
    public String prakasamSpot() {
        return "Prakasam-Tourism";
    }

    @GetMapping("/Tourism/Prakasam/BhairavaKona")
    public String getBhairavaKona() {
        return "Tourism/Prakasam/BhairavaKona";
    }

    @GetMapping("/Tourism/Prakasam/PakalaBeach")
    public String getPakalaBeach() {
        return "Tourism/Prakasam/PakalaBeach";
    }

    @GetMapping("/Tourism/Prakasam/KothaPatnamBeach")
    public String getKothaPatnamBeach() {
        return "Tourism/Prakasam/KothaPatnamBeach";
    }

    @GetMapping("/Tourism/Prakasam/Tripuranthakam")
    public String getTripuranthakam() {
        return "Tourism/Prakasam/Tripuranthakam";
    }

    @GetMapping("/Sri-Potti-Sriramulu-Nellore-Tourism")
    public String SriPottiSriramuluNelloreSpot() {
        return "Sri-Potti-Sriramulu-Nellore-Tourism";
    }

    @GetMapping("/Tourism/Sri-Potti-Sriramulu-Nellore/Jonnawada")
    public String getJonnawada() {
        return "Tourism/Sri-Potti-Sriramulu-Nellore/Jonnawada";
    }

    @GetMapping("/Tourism/Sri-Potti-Sriramulu-Nellore/Kandaleru_Dam")
    public String getKandaleruDam() {
        return "Tourism/Sri-Potti-Sriramulu-Nellore/Kandaleru_Dam";
    }

    @GetMapping("/Tourism/Sri-Potti-Sriramulu-Nellore/SomasilaDam")
    public String getSomasilaDam() {
        return "Tourism/Sri-Potti-Sriramulu-Nellore/SomasilaDam";
    }

    @GetMapping("/Tourism/Sri-Potti-Sriramulu-Nellore/Talapagiri_Ranganatha_Swamy_Temple")
    public String getTalapagiriRanganathaSwamyTemple() {
        return "Tourism/Sri-Potti-Sriramulu-Nellore/Talapagiri_Ranganatha_Swamy_Temple";
    }

    @GetMapping("/Tourism/Sri-Potti-Sriramulu-Nellore/Udayagiri_Fort")
    public String getUdayagiriFort() {
        return "Tourism/Sri-Potti-Sriramulu-Nellore/Udayagiri_Fort";
    }

    @GetMapping("/Tourism/Sri-Potti-Sriramulu-Nellore/NarasimhaKonda")
    public String getNarasimhaKonda() {
        return "Tourism/Sri-Potti-Sriramulu-Nellore/NarasimhaKonda";
    }

    @GetMapping("/Tourism/Sri-Potti-Sriramulu-Nellore/Penchalakona")
    public String getPenchalakona() {
        return "Tourism/Sri-Potti-Sriramulu-Nellore/Penchalakona";
    }

    @GetMapping("/Visakhapatnam-Tourism")
    public String vizagSpot() {
        return "Visakhapatnam-Tourism";
    }

    @GetMapping("/Tourism/Visakhapatnam/Kailasgiri")
    public String getKailasgiri() {
        return "Tourism/Visakhapatnam/Kailasgiri";
    }

    @GetMapping("/Tourism/Visakhapatnam/Rkbeach")
    public String getrkbeach() {
        return "Tourism/Visakhapatnam/Rkbeach";
    }

    @GetMapping("/Tourism/Visakhapatnam/Rushikonda")
    public String getRushikonda() {
        return "Tourism/Visakhapatnam/Rushikonda";
    }

    @GetMapping("/Tourism/Visakhapatnam/Simhachalam")
    public String getSimhachalam() {
        return "Tourism/Visakhapatnam/Simhachalam";
    }

    @GetMapping("/Tourism/Visakhapatnam/Vudapark")
    public String getVuda() {
        return "Tourism/Visakhapatnam/Vudapark";
    }

    @GetMapping("/Tourism/Visakhapatnam/yaradabeach")
    public String getyaradabeach() {
        return "Tourism/Visakhapatnam/yaradabeach";
    }

    @GetMapping("/Tourism/Visakhapatnam/ttdtemplevizag")
    public String getttdtemplevizag() {
        return "Tourism/Visakhapatnam/ttdtemplevizag";
    }

    @GetMapping("/Tourism/Visakhapatnam/aircraftmuseum")
    public String getaircraftmuseum() {
        return "Tourism/Visakhapatnam/aircraftmuseum";
    }

    @GetMapping("/Tourism/Visakhapatnam/TenetiPark")
    public String getTenetiPark() {
        return "Tourism/Visakhapatnam/TenetiPark";
    }

    @GetMapping("/Sri-Sathya-Sai-Tourism")
    public String SriSathyaSaiSpot() {
        return "Sri-Sathya-Sai-Tourism";
    }

    @GetMapping("Tourism/Sri-Sathya-Sai/Hemavathi")
    public String getHemavathi() {
        return "Tourism/Sri-Sathya-Sai/Hemavathi";
    }

    @GetMapping("Tourism/Sri-Sathya-Sai/Lepakshi")
    public String getLepakshi() {
        return "Tourism/Sri-Sathya-Sai/Lepakshi";
    }

    @GetMapping("Tourism/Sri-Sathya-Sai/Nimmalakunta")
    public String getNimmalakunta() {
        return "Tourism/Sri-Sathya-Sai/Nimmalakunta";
    }

    @GetMapping("Tourism/Sri-Sathya-Sai/Penukonda")
    public String getPenukonda() {
        return "Tourism/Sri-Sathya-Sai/Penukonda";
    }

    @GetMapping("Tourism/Sri-Sathya-Sai/Puttaparthi")
    public String getPuttaparthi() {
        return "Tourism/Sri-Sathya-Sai/Puttaparthi";
    }

    @GetMapping("Tourism/Sri-Sathya-Sai/Sri-kadiri-laxmi-narasimha-swamy-temple")
    public String getKadiriLaxmiTemple() {
        return "Tourism/Sri-Sathya-Sai/Sri-kadiri-laxmi-narasimha-swamy-temple";
    }

    @GetMapping("Tourism/Sri-Sathya-Sai/Yogivemana-Samadhi")
    public String getYogivemana() {
        return "Tourism/Sri-Sathya-Sai/Yogivemana";
    }

    @GetMapping("/Vzm-Tourism")
    public String ViziangaramSpot() {
        return "Vzm-Tourism";
    }

    @GetMapping("/Tourism/Vzm/Bobbili")
    public String getBobbili() {
        return "Tourism/Vzm/Bobbili";
    }

    @GetMapping("/Tourism/Vzm/fort")
    public String getFort() {
        return "Tourism/Vzm/fort";
    }

    @GetMapping("/Tourism/Vzm/Govindapuram")
    public String getGovindapuram() {
        return "Tourism/Vzm/Govindapuram";
    }

    @GetMapping("/Tourism/Vzm/Punyagiri")
    public String getPunyagiri() {
        return "Tourism/Vzm/Punyagiri";
    }

    @GetMapping("/Tourism/Vzm/Ramanarayanam")
    public String getRamanarayanam() {
        return "Tourism/Vzm/Ramanarayanam";
    }

    @GetMapping("/Tourism/Vzm/Ramateertham")
    public String getRamateertham() {
        return "Tourism/Vzm/Ramateertham";
    }

    @GetMapping("/Tourism/Vzm/Saraswathi")
    public String getSaraswathi() {
        return "Tourism/Vzm/Saraswathi";
    }

    @GetMapping("/West-Tourism")
    public String WestGodavariSpot() {
        return "West-Tourism";
    }

    @GetMapping("/Tourism/WestGodavari/Sanctuary")
    public String getSanctuary() {
        return "Tourism/WestGodavari/Sanctuary";
    }

    @GetMapping("/Tourism/WestGodavari/Ramalingeswara")
    public String getRamalingeswara() {
        return "Tourism/WestGodavari/Ramalingeswara";
    }

    @GetMapping("/Tourism/WestGodavari/Papikondalu")
    public String getPapikondalu() {
        return "Tourism/WestGodavari/Papikondalu";
    }

    @GetMapping("/Tourism/WestGodavari/Perupalem")
    public String getPerupalem() {
        return "Tourism/WestGodavari/Perupalem";
    }

    @GetMapping("/Tourism/WestGodavari/Someswara")
    public String getSomeswara() {
        return "Tourism/WestGodavari/Someswara";
    }

    // nandyala controller
    @GetMapping("/Nandyala-Tourism")
    public String NandyalaSpot() {
        return "Nandyala-Tourism";
    }

    @GetMapping("/Tourism/Nandyala/yaganti")
    public String getyaganti() {
        return "Tourism/Nandyala/yaganti";
    }

    @GetMapping("/Tourism/Nandyala/ahobilam")
    public String getahobilam() {
        return "Tourism/Nandyala/ahobilam";
    }

    @GetMapping("/Tourism/Nandyala/rollapadu")
    public String getRollapadu() {
        return "Tourism/Nandyala/rollapadu";
    }

    @GetMapping("/Tourism/Nandyala/Mahanandi")
    public String getMahanandi() {
        return "Tourism/Nandyala/Mahanandi";
    }

    @GetMapping("/Tourism/Nandyala/Srisailam")
    public String getSrisailam() {
        return "Tourism/Nandyala/Srisailam";
    }

    @GetMapping("/Tourism/Nandyala/SrisailamDam")
    public String getSrisailamDam() {
        return "Tourism/Nandyala/SrisailamDam";
    }

    @GetMapping("/Tourism/Nandyala/Belumcaves")
    public String getBelumcaves() {
        return "Tourism/Nandyala/Belumcaves";
    }

    // YSR controller
    @GetMapping("/YSR-Tourism")
    public String YSRKadapaSpot() {
        return "YSR-Tourism";
    }

    @GetMapping("/Tourism/YSR/Ameen-Peer-Dargah")
    public String getDargah() {
        return "Tourism/YSR/Ameen-Peer-Dargah";
    }

    @GetMapping("/Tourism/YSR/Attirala")
    public String getsAttirala() {
        return "Tourism/YSR/Attirala";
    }

    @GetMapping("/Tourism/YSR/BelumCaves")
    public String getBelumCaves() {
        return "Tourism/YSR/BelumCaves";
    }

    @GetMapping("/Tourism/YSR/BramhamGariMatam")
    public String getBramhamGariMatam() {
        return "Tourism/YSR/BramhamGariMatam";
    }

    @GetMapping("/Tourism/YSR/Gandikota")
    public String getGandikota() {
        return "Tourism/YSR/Gandikota";
    }

    @GetMapping("/Tourism/YSR/kamakshi")
    public String getKamakshi() {
        return "Tourism/YSR/kamakshi";
    }

    @GetMapping("/Tourism/YSR/lankamalleswara")
    public String getLankamalleswara() {
        return "Tourism/YSR/lankamalleswara";
    }

    @GetMapping("/Tourism/YSR/museum")
    public String getMuseum() {
        return "Tourism/YSR/museum";
    }

    @GetMapping("/Tourism/YSR/Palakondalu")
    public String getPalakonda() {
        return "Tourism/YSR/Palakondalu";
    }

    @GetMapping("/Tourism/YSR/Shilparamam")
    public String getShilparamam() {
        return "Tourism/YSR/Shilparamam";
    }

    @GetMapping("/Tourism/YSR/SidhavatamFort")
    public String getSidhavatamFort() {
        return "Tourism/YSR/SidhavatamFort";
    }

    @GetMapping("/Tourism/YSR/veera-anjaneya-gandi-kshethram")
    public String getKshethram() {
        return "Tourism/YSR/veera-anjaneya-gandi-kshethram";
    }

    @GetMapping("/Tourism/YSR/veerabhadra")
    public String getVeerabhadra() {
        return "Tourism/YSR/veerabhadra";
    }

    @GetMapping("/Tourism/YSR/Vontimitta")
    public String getVontimitta() {
        return "Tourism/YSR/Vontimitta";
    }

    @GetMapping("/Krishna-Tourism")
    public String KrishnaSpot() {
        return "Krishna-Tourism";
    }

    @GetMapping("/Tourism/Krishna/Kondapallitoys")
    public String getKondapallitoys() {
        return "Tourism/Krishna/Kondapallitoys";
    }

    @GetMapping("/Tourism/Krishna/Vijayawada")
    public String getVijayawada() {
        return "Tourism/Krishna/Vijayawada";
    }

    @GetMapping("/Tourism/Krishna/PrakasamBarage")
    public String getPrakasamBarage() {
        return "Tourism/Krishna/PrakasamBarage";
    }

    // palnadu controller
    @GetMapping("/Palnadu-Tourism")
    public String PalnaduSpot() {
        return "Palnadu-Tourism";
    }

    @GetMapping("/Tourism/Palnadu/Ethipothala")
    public String getEthipothala() {
        return "Tourism/Palnadu/Ethipothala";
    }

    @GetMapping("/Tourism/Palnadu/Kotappakonda")
    public String getKotappakonda() {
        return "Tourism/Palnadu/Kotappakonda";
    }

    @GetMapping("/Tourism/Palnadu/Nagarjunasagardam")
    public String getNagarjunasagardam() {
        return "Tourism/Palnadu/Nagarjunasagardam";
    }

    // EastGodavari controller
    @GetMapping("/EastGodavari-Tourism")
    public String EastGodavariSpot() {
        return "EastGodavari-Tourism";
    }

    @GetMapping("/Tourism/EastGodavari/Jaganmohini")
    public String getJaganMohini() {
        return "Tourism/EastGodavari/Jaganmohini";
    }

    @GetMapping("/Tourism/EastGodavari/Pithapuram")
    public String getPithapuram() {
        return "Tourism/EastGodavari/Pithapuram";
    }

    @GetMapping("/Tourism/EastGodavari/HopeIsland")
    public String getHopeIsland() {
        return "Tourism/EastGodavari/HopeIsland";
    }

    @GetMapping("/Tourism/EastGodavari/CottonMuseum")
    public String getCottonMuseum() {
        return "Tourism/EastGodavari/CottonMuseum";
    }

    // Eluru controller
    @GetMapping("/Eluru-Tourism")
    public String EluruSpot() {
        return "Eluru-Tourism";
    }

    @GetMapping("/Tourism/Eluru/kolleru")
    public String getKolleru() {
        return "Tourism/Eluru/kolleru";
    }

    @GetMapping("/Tourism/Eluru/Buddhist")
    public String getBuddhist() {
        return "Tourism/Eluru/Buddhist";
    }

    @GetMapping("/Tourism/Eluru/dwarakatirumala")
    public String getdwarakatirumala() {
        return "Tourism/Eluru/dwarakatirumala";
    }

    @GetMapping("/Tourism/Eluru/ashram")
    public String getashram() {
        return "Tourism/Eluru/ashram";
    }

    @GetMapping("/Tourism/Eluru/guntupalli")
    public String getguntupalli() {
        return "Tourism/Eluru/guntupalli";
    }

    // Guntur controller
    @GetMapping("/Guntur-Tourism")
    public String GunturSpot() {
        return "Guntur-Tourism";
    }

    @GetMapping("/Tourism/Guntur/Amaravathi")
    public String getAmaravathi() {
        return "Tourism/Guntur/Amaravathi";
    }

    @GetMapping("/Tourism/Guntur/Mangalagiri_Narasimha_Swamy_Temple")
    public String getMangalagiri_Narasimha_Swamy_TempleString() {
        return "Tourism/Guntur/Mangalagiri_Narasimha_Swamy_Temple";
    }

    @GetMapping("/Tourism/Guntur/Uppalapadu_Bird_Sanctuary")
    public String getUppalapadu_Bird_Sanctuary() {
        return "Tourism/Guntur/Uppalapadu_Bird_Sanctuary";
    }

    @GetMapping("/Tourism/Guntur/Kondaveedu")
    public String getKondaveedu() {
        return "Tourism/Guntur/Kondaveedu";
    }

    @GetMapping("/Tourism/Guntur/KrishnaBarrage")
    public String getKrishnaBarrage() {
        return "Tourism/Guntur/KrishnaBarrage";
    }

    // Kakinada controller
    @GetMapping("/Kakinada-Tourism")
    public String KakinadaSpot() {
        return "Kakinada-Tourism";
    }

    @GetMapping("/Tourism/Kakinada/Annavaram")
    public String getAnnavaram() {
        return "Tourism/Kakinada/Annavaram";
    }

    @GetMapping("/Tourism/Kakinada/Beach")
    public String getBeach() {
        return "Tourism/Kakinada/Beach";
    }

    @GetMapping("/Tourism/Kakinada/yanam")
    public String getyanam() {
        return "Tourism/Kakinada/yanam";
    }

    @GetMapping("/Tourism/Kakinada/Coringa")
    public String getCoringa() {
        return "Tourism/Kakinada/Coringa";
    }

    // Konaseema controller
    @GetMapping("/Konaseema-Tourism")
    public String KonaseemaSpot() {
        return "Konaseema-Tourism";
    }

    @GetMapping("/Tourism/Konaseema/Draksharam")
    public String getDraksharam() {
        return "Tourism/Konaseema/Draksharam";
    }

    @GetMapping("/Tourism/Konaseema/Backwaters")
    public String getBackwaters() {
        return "Tourism/Konaseema/Backwaters";
    }

    @GetMapping("/Tourism/Konaseema/Vodalarevu")
    public String getVodalarevu() {
        return "Tourism/Konaseema/Vodalarevu";
    }

    @GetMapping("/Tourism/Konaseema/Antarvedi")
    public String getAntarvedi() {
        return "Tourism/Konaseema/Antarvedi";
    }

    // Kurnool controller
    @GetMapping("/Kurnool-Tourism")
    public String KurnoolSpot() {
        return "Kurnool-Tourism";
    }

    @GetMapping("/Tourism/Kurnool/gandikota")
    public String getgandikota() {
        return "Tourism/Kurnool/gandikota";
    }

    @GetMapping("/Tourism/Kurnool/kondareddy")
    public String getkondareddy() {
        return "Tourism/Kurnool/kondareddy";
    }

    @GetMapping("/Tourism/Kurnool/mantralayam")
    public String getmantralayam() {
        return "Tourism/Kurnool/mantralayam";
    }

    @GetMapping("/Tourism/Kurnool/sakshiganapathi")
    public String getSakshiganapathi() {
        return "Tourism/Kurnool/sakshiganapathi";
    }

    @GetMapping("/Tirupathi-Tourism")
    public String TirupathiSpot() {
        return "Tirupathi-Tourism";
    }

    @GetMapping("/Tourism/Tirupathi/Zoo")
    public String getZoo() {
        return "Tourism/Tirupathi/Zoo";
    }

    @GetMapping("/Tourism/Tirupathi/Kalahasti")
    public String getkalahasti() {
        return "Tourism/Tirupathi/Kalahasti";
    }

    @GetMapping("/Tourism/Tirupathi/TTD")
    public String getTTD() {
        return "Tourism/Tirupathi/TTD";
    }

    @GetMapping("/Tourism/Tirupathi/Aakashaganga")
    public String getAakashaganga() {
        return "Tourism/Tirupathi/Aakashaganga";
    }

    @GetMapping("/Tourism/Tirupathi/Alipiri")
    public String getAlipiri() {
        return "Tourism/Tirupathi/Alipiri";
    }

    @GetMapping("/Tourism/Tirupathi/Japalitheertham")
    public String getJapalitheertham() {
        return "Tourism/Tirupathi/JapaliTeertham";
    }

    @GetMapping("/Tourism/Tirupathi/Kapila")
    public String getKapila() {
        return "Tourism/Tirupathi/Kapila";
    }

    @GetMapping("/Tourism/Tirupathi/Padmavathitemple")
    public String getPadmavathitemple() {
        return "Tourism/Tirupathi/Padmavathitemple";
    }

    @GetMapping("/Tourism/Tirupathi/Papavinasam")
    public String getPapavinasam() {
        return "Tourism/Tirupathi/Papavinasam";
    }

    @GetMapping("/Tourism/Tirupathi/Mamandor")
    public String getMamandor() {
        return "Tourism/Tirupathi/Mamandor";
    }

    @GetMapping("/Tourism/Tirupathi/DeerPark")
    public String getDeerPark() {
        return "Tourism/Tirupathi/DeerPark";
    }

    @GetMapping("/Tourism/Tirupathi/ChandragiriFort")
    public String getChandragiriFort() {
        return "Tourism/Tirupathi/ChandragiriFort";
    }

    @GetMapping("/Tourism/Tirupathi/KalyaniDam")
    public String getKalyaniDam() {
        return "Tourism/Tirupathi/KalyaniDam";
    }

    @GetMapping("/Tourism/Tirupathi/RayalaCheruvu")
    public String getRayalaCheruvu() {
        return "Tourism/Tirupathi/RayalaCheruvu";
    }

    @GetMapping("/Tourism/Tirupathi/RegionalScienceCentre")
    public String getRegionalScienceCentre() {
        return "Tourism/Tirupathi/RegionalScienceCentre";
    }

    @GetMapping("/Tourism/Tirupathi/sadasivakona")
    public String getsadasivakona() {
        return "Tourism/Tirupathi/sadasivakona";
    }

    @GetMapping("/Tourism/Tirupathi/silathoranamGarden")
    public String getsilathoranamGarden() {
        return "Tourism/Tirupathi/silathoranamGarden";
    }

    @GetMapping("/Tourism/Tirupathi/SriBediAnjaneyaSwamyTemple")
    public String getSriBediAnjaneyaSwamyTemple() {
        return "Tourism/Tirupathi/SriBediAnjaneyaSwamyTemple";
    }

    @GetMapping("/Tourism/Tirupathi/SriGovindarajaswamyTemple")
    public String getSriGovindarajaswamyTempler() {
        return "Tourism/Tirupathi/SriGovindarajaswamyTemple";
    }

    @GetMapping("/Tourism/Tirupathi/Talakona")
    public String getTalakona() {
        return "Tourism/Tirupathi/Talakona";
    }

    @GetMapping("/Tourism/Tirupathi/svmuseum")
    public String getsvmuseum() {
        return "Tourism/Tirupathi/svmuseum";
    }

    @GetMapping("/Srikakulam-Tourism")
    public String Srikakulam() {
        return "Srikakulam-Tourism";
    }

    @GetMapping("/Tourism/Srikakulam/SrikakulamTemple")
    public String getSrikakulamTemple() {
        return "/Tourism/Srikakulam/SrikakulamTemple";
    }

    @GetMapping("/Tourism/Srikakulam/KalingapatnamBeach")
    public String getKalingapatnamBeach() {
        return "/Tourism/Srikakulam/KalingapatnamBeach";
    }

    @GetMapping("/Tourism/Srikakulam/TelineelapuramSanctuary")
    public String getTelineelapuramSanctuary() {
        return "/Tourism/Srikakulam/TelineelapuramSanctuary";
    }

    @GetMapping("/Tourism/Srikakulam/Arasavilli")
    public String getSrikakulamFort() {
        return "/Tourism/Srikakulam/Arasavilli";
    }

    @GetMapping("/Tourism/Srikakulam/srimukhalingamtemple")
    public String getsrimukhalingamtemple() {
        return "/Tourism/Srikakulam/srimukhalingamtemple";
    }

    @GetMapping("/Tourism/Srikakulam/Salihundam")
    public String getSalihundam() {
        return "/Tourism/Srikakulam/Salihundam";
    }

    @GetMapping("/Tourism/Srikakulam/BaruvaBeach")
    public String getBaruvaBeach() {
        return "/Tourism/Srikakulam/BaruvaBeach";
    }

    @GetMapping("/Tourism/Srikakulam/MandasaVasudevaTemple")
    public String getMandasaVasudevaTemple() {
        return "/Tourism/Srikakulam/MandasaVasudevaTemple";
    }

    @GetMapping("/Tourism/Srikakulam/KotilingalaRevu")
    public String getKotilingalaRevu() {
        return "/Tourism/Srikakulam/KotilingalaRevu";
    }

    @GetMapping("/Parvathipuram-Tourism")
    public String Parvathipuram() {
        return "Parvathipuram-Tourism";
    }

    @GetMapping("/Tourism/Parvathipuram/ThotapalliKodandaramaTemple")
    public String getThotapalliKodandarama() {
        return "/Tourism/Parvathipuram/ThotapalliKodandaramaTemple";
    }

    @GetMapping("/Tourism/Parvathipuram/ThotapalliBarrage")
    public String getThotapalliBarrage() {
        return "/Tourism/Parvathipuram/ThotapalliBarrage";
    }

    @GetMapping("/Tourism/Parvathipuram/ThonamWaterfalls")
    public String getThonamWaterfalls() {
        return "/Tourism/Parvathipuram/ThonamWaterfalls";
    }

    @GetMapping("/Tourism/Parvathipuram/Tadikondawaterfalls")
    public String getSuryalankabeach() {
        return "/Tourism/Parvathipuram/Tadikondawaterfalls";
    }

    @GetMapping("/Tourism/Parvathipuram/ITDA")
    public String getITDA() {
        return "/Tourism/Parvathipuram/ITDAPark";
    }

    /* probably used */
    @GetMapping("/Tourism/Parvathipuram/SambaraPolamabaTemple")
    public String getPVodarevu() {
        return "/Tourism/Parvathipuram/SambaraPolamabaTemple";
    }

    @GetMapping("/Tourism/Parvathipuram/KaasiVisweswaratemple")
    public String getKaasiVisweswaratemple() {
        return "/Tourism/Parvathipuram/KaasiVisweswaratemple";
    }

    @GetMapping("/Tourism/Parvathipuram/ITDAPark")
    public String getITDAPark() {
        return "/Tourism/Parvathipuram/ITDAPark";
    }

    @GetMapping("/about")
    public String About() {
        return "About";
    }

    @GetMapping("/contact")
    public String Contact() {
        return "Contact";
    }

    @Autowired
    private UserTourismRepository userRepository;

    @Autowired
    private UserTourismService userService;

    @Autowired
    private UserDetails userDetails;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserTourism());
        return "signup";
    }

    @PostMapping("/processR")
    public String processRegister(@Valid @ModelAttribute("user") UserTourism theUser,
            BindingResult theBindingResult,
            Model model, RedirectAttributes redirectAttributes) {
        if (theBindingResult.hasErrors()) {
            model.addAttribute("signupActive", true); // Keep signup form active
            return "signup"; // Return to the signup page with errors
        }

        // Check if email already exists
        if (userRepository.findByEmail(theUser.getEmail()) != null) {
            theBindingResult.rejectValue("email", "error.user", "Email already registered");
            model.addAttribute("signupActive", true); // Keep signup form active
            return "signup"; // Return to the signup page with errors
        }

        if (userRepository.findByMobile(theUser.getMobile()) != null) {
            theBindingResult.rejectValue("mobile", "error.user", "Mobile number already registered");
            model.addAttribute("signupActive", true); // Keep signup form active
            return "signup"; // Return to the signup page with errors
        }

        // Proceed to save the user
        userRepository.save(theUser);

        // Add flash attribute for success message
        redirectAttributes.addFlashAttribute("successMessage", "You are successfully registered");
        redirectAttributes.addFlashAttribute("notificationType", "success");

        return "redirect:/register#container"; // Redirect to the home page upon successful registration
    }

    @PostMapping("/processL")
    public String login(@RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model,
            HttpSession session) {
        // Create a new User object
        UserTourism user = new UserTourism();
        model.addAttribute("user", user); // Add user to model to keep form data

        // Check if email is empty
        if (email == null || email.isEmpty()) {
            model.addAttribute("emailError", "Email cannot be empty.");
            return "signup"; // Show the login page again if email is empty
        }

        // Validate email format
        if (!emailIsValid(email)) {
            model.addAttribute("emailError", "Invalid email format.");
            return "signup"; // Show the login page again if email format is invalid
        }

        // Check if password is empty
        if (password == null || password.isEmpty()) {
            model.addAttribute("passwordError", "Password cannot be empty.");
            return "signup"; // Show the login page again if password is empty
        }

        // Authenticate the user
        UserTourism authenticatedUser = userService.authenticate(email, password);

        // Check for authentication
        if (authenticatedUser != null) {
            // model.addAttribute("message", "Login successful!");
            // session.setAttribute("email", email);
            UserTourism userDets = userDetails.getUserDetails(email, password);
            session.setAttribute("userDets", userDets);
            return "redirect:/"; // Redirect to home or user dashboard upon successful login
        } else {
            // If authentication fails, we need to check if the email exists
            if (!userService.emailExists(email)) {
                model.addAttribute("emailError", "Email not registered."); // Email is invalid
            } else {
                model.addAttribute("passwordError", "Invalid password."); // Password is wrong
            }
            return "signup"; // Show the login page again with appropriate error messages
        }
    }

    // Helper method to check if the email format is valid
    private boolean emailIsValid(String email) {
        // Implement your email validation logic here (e.g., regex check)
        return email.contains("@"); // Simple check; improve this with regex for production use
    }
    // Entertainment

    @GetMapping("/Entertainment/home")
    public String entertainmentPage() {
        return "/Entertainment/home";
    }

    @GetMapping("/Entertainment/Movies")
    public String entertainmentMoviesPage() {
        return "/Entertainment/movies";
    }

    @GetMapping("/Entertainment/MyMusic")
    public String entertainmentMusicPage() {
        return "/Entertainment/Mymusic";
    }

    @GetMapping("/Entertainment/ShortFilms")
    public String entertainmentShortFilmsPage() {
        return "/Entertainment/telugu";
    }

    @GetMapping("/Entertainment/FilmNews")
    public String entertainmentFilmPage() {
        return "/Entertainment/fil";
    }

}
