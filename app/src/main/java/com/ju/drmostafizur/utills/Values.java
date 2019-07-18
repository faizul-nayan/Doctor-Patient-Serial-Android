package com.ju.drmostafizur.utills;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Faizul Haque Nayan on 8/28/17.
 */

public class Values {

    public static final String DEPOSIT = "Deposit";
    public static final String WITHDRAW = "Withdraw";
    public static final String MEMBER_NAME = "memberName";
    public static final String MEMBER_CODE = "memberCode";
    public static final String DATA = "data";
    public static final String SELECT = "-----Select-----";
    public static final String SAMITY = "Samity";
    public static final String MEMBER_ID = "memberId";
    public static final String BANK = "BANK";
    public static final String CASH = "CASH";
    public static final String NULL = "";
    public static final String LOANTRANSACTIONDETAILS = "LoanTransactionDetailsModel";
    public static final String MEMBER = "Member";
    public static final String LOAN_DETAILS = "LoanDetails";
    public static final String SAVING_DETAILS = "SavingDetails";
    public static final String IMAGE_FOLDER_NAME = "dr-mostafiz";
    public static final String PURCHES = "Purches";
    public static final String SHAREDEPOSITDITHDRAW = "ShareDepositWithdraw";
    public static final String LOANTRANSACTION = "loan_transaction";
    //public static final String LOANTRANSACTION = "LoanTransaction";
    public static final String SHAREDEPOSIT = "ShareDeposit";
    public static final String SHAREWITHDRAW = "ShareWithdraw";
    public static final String SHARE = "Share";
    public static final String isObsolute = "isObsolute";
    public static final String REFUND = "Refund";
    public static final String RECOVERABLE = "Recoverable";
    public static final String RECOVERY = "Recovery";
    public static final String LABELNAME = "SAMITY LABEL NAME";
    public static final String DEFAULTLABELNAME = "Group";
    public static final String MISBWDAILYINFO = "MisBWDailyInfo";
    public static final String CUSTOMBWDAILY = "CustomBWDaily";
    public static final String REPORTDATA = "ReportData";
    public static final String COLOR = "Color";
    public static final String LOANPENALTY = "LoanPenalty";
    public static final String LOAEOVERDUE = "LoanOverDue";
    public static final String PATIENT_BOOKED_MODEL = "patient_booked";

//    public static final String BASE_URL = "http://192.168.1.103/";
    public static final String BASE_URL = "http://192.168.2.12/";
    //#20@#coop!16 browser
    //coop@#mar!16 skype
    //public static final String BASE_URL = "http://192.168.1.73/";
//    public static final String BASE_URL = "http://cooperative360.com/";

    public final CharSequence[] chooseOptions = { "Take Photo", "Choose from Library",
            "Cancel" };

    public static final ArrayList<String> paymentTypeList() {
        ArrayList<String> list = new ArrayList();
        list.add("CASH");
        list.add("BANK");
        return list;
    }

    public static final ArrayList<String> bloodGroupList(){
        ArrayList<String> list = new ArrayList();
        list.add("A+");
        list.add("A-");
        list.add("B+");
        list.add("B-");
        list.add("o+");
        list.add("o-");
        list.add("AB+");
        list.add("AB-");
        return list;
    }

    public static final ArrayList<String> employeerTypeList() {
        ArrayList<String> list = new ArrayList();
        list.add("Permanent");
        list.add("Part-Time");
        list.add("Contractual");
        list.add("Guest");
        return list;
    }

    public static final ArrayList<String> genderList(){
        ArrayList<String> list = new ArrayList<>();
        list.add("Male");
        list.add("Female");
        return list;
    }


    public static final ArrayList<String> maritalStatus(){
        ArrayList<String> list = new ArrayList<>();
        list.add("Single");
        list.add("Married");
        list.add("Divorced");
        list.add("Widowed");
        return list;
    }

    public static final ArrayList<String> relationship(){
        ArrayList<String> list = new ArrayList<>();
        list.add(SELECT);
        list.add("Father");
        list.add("Spouse");
        list.add("Son");
        list.add("Brother");
        list.add("Sister");
        list.add("Cousin");
        return list;
    }

    public static final ArrayList<String> educationLevel(){
        ArrayList<String> list = new ArrayList<>();
        list.add(SELECT);
        list.add("H.S.C");
        list.add("S.S.C");
        list.add("Primary");
        list.add("B A");
        list.add("B.Com");
        list.add("B.Sc");
        list.add("Masters");
        list.add("B.S.S");
        list.add("MBA");
        return list;
    }


    public static final ArrayList<String> countryName(){

        String[] myStringArray = new String[]{SELECT,"Afghanistan", "Albania", "Algeria",
                "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica",
                "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia",
                "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados",
                "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia",
                "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil",
                "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria",
                "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde",
                "Cayman Islands", "Central African Republic", "Chad", "Chile", "China",
                "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo",
                "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica",
                "Cote d'Ivoire", "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic",
                "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor",
                "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea",
                "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands",
                "Fiji", "Finland", "France", "France Metropolitan", "French Guiana",
                "French Polynesia", "French Southern Territories", "Gabon", "Gambia",
                "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland",
                "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau",
                "Guyana", "Haiti", "Heard and Mc Donald Islands",
                "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland",
                "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel",
                "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea," +
                " Democratic People's Republic of", "Korea, Republic of", "Kuwait", "Kyrgyzstan",
                "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia",
                "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau",
                "Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia",
                "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania",
                "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, " +
                "Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique",
                "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles",
                "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue",
                "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan",
                "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines",
                "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania",
                "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia",
                "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe",
                "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia (Slovak Republic)",
                "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands",
                "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname",
                "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic",
                "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo",
                "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands",
                "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States",
                "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela",
                "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands",
                "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe"};
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(myStringArray));
        return list;
    }
}
