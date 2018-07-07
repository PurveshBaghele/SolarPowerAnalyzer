package com.example.administrator.mymapapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Profile extends AppCompatActivity {


    String names[]={"Adilabad",
            "Agra" ,
            "Ahmedabad" ,
            "Ahmednagar" ,
            "Aizawl" ,
            "Ajitgarh (Mohali)" ,
            "Ajmer" ,
            "Akola" ,
            "Alappuzha" ,
            "Aligarh" ,
            "Alirajpur" ,
            "Allahabad" ,
            "Almora" ,
            "Alwar" ,
            "Ambala" ,
            "Ambedkar Nagar" ,
            "Amravati" ,
            "Amreli district" ,
            "Amritsar" ,
            "Anand" ,
            "Anantapur" ,
            "Anantnag" ,
            "Angul" ,
            "Anjaw" ,
            "Anuppur" ,
            "Araria" ,
            "Ariyalur" ,
            "Arwal" ,
            "Ashok Nagar" ,
            "Auraiya" ,
            "Aurangabad" ,
            "Aurangabad" ,
            "Azamgarh" ,
            "Badgam" ,
            "Bagalkot" ,
            "Bageshwar" ,
            "Bagpat" ,
            "Bahraich" ,
            "Baksa" ,
            "Balaghat" ,
            "Balangir" ,
            "Balasore" ,
            "Ballia" ,
            "Balrampur" ,
            "Banaskantha" ,
            "Banda" ,
            "Bandipora" ,
            "Bangalore Rural" ,
            "Bangalore Urban" ,
            "Banka" ,
            "Bankura" ,
            "Banswara" ,
            "Barabanki" ,
            "Baramulla" ,
            "Baran" ,
            "Bardhaman" ,
            "Bareilly" ,
            "Bargarh (Baragarh)" ,
            "Barmer" ,
            "Barnala" ,
            "Barpeta" ,
            "Barwani" ,
            "Bastar" ,
            "Basti" ,
            "Bathinda" ,
            "Beed" ,
            "Begusarai" ,
            "Belgaum" ,
            "Bellary" ,
            "Betul" ,
            "Bhadrak" ,
            "Bhagalpur" ,
            "Bhandara" ,
            "Bharatpur" ,
            "Bharuch" ,
            "Bhavnagar" ,
            "Bhilwara" ,
            "Bhind" ,
            "Bhiwani" ,
            "Bhojpur" ,
            "Bhopal" ,
            "Bidar" ,
            "Bijapur" ,
            "Bijapur" ,
            "Bijnor" ,
            "Bikaner" ,
            "Bilaspur" ,
            "Bilaspur" ,
            "Birbhum" ,
            "Bishnupur" ,
            "Bokaro" ,
            "Bongaigaon" ,
            "Boudh (Bauda)" ,
            "Budaun" ,
            "Bulandshahr" ,
            "Buldhana" ,
            "Bundi" ,
            "Burhanpur" ,
            "Buxar" ,
            "Cachar" ,
            "Central Delhi" ,
            "Chamarajnagar" ,
            "Chamba" ,
            "Chamoli" ,
            "Champawat" ,
            "Champhai" ,
            "Chandauli" ,
            "Chandel" ,
            "Chandigarh" ,
            "Chandrapur" ,
            "Changlang" ,
            "Chatra" ,
            "Chennai" ,
            "Chhatarpur" ,
            "Chhatrapati Shahuji Maharaj Nagar" ,
            "Chhindwara" ,
            "Chikkaballapur" ,
            "Chikkamagaluru" ,
            "Chirang" ,
            "Chitradurga" ,
            "Chitrakoot" ,
            "Chittoor" ,
            "Chittorgarh" ,
            "Churachandpur" ,
            "Churu" ,
            "Coimbatore" ,
            "Cooch Behar" ,
            "Cuddalore" ,
            "Cuttack" ,
            "Dadra and Nagar Haveli" ,
            "Dahod" ,
            "Dakshin Dinajpur" ,
            "Dakshina Kannada" ,
            "Daman" ,
            "Damoh" ,
            "Dantewada" ,
            "Darbhanga" ,
            "Darjeeling" ,
            "Darrang" ,
            "Datia" ,
            "Dausa" ,
            "Davanagere" ,
            "Debagarh (Deogarh)" ,
            "Dehradun" ,
            "Deoghar" ,
            "Deoria" ,
            "Dewas" ,
            "Dhalai" ,
            "Dhamtari" ,
            "Dhanbad" ,
            "Dhar" ,
            "Dharmapuri" ,
            "Dharwad" ,
            "Dhemaji" ,
            "Dhenkanal" ,
            "Dholpur" ,
            "Dhubri" ,
            "Dhule" ,
            "Dibang Valley" ,
            "Dibrugarh" ,
            "Dima Hasao" ,
            "Dimapur" ,
            "Dindigul" ,
            "Dindori" ,
            "Diu" ,
            "Doda" ,
            "Dumka" ,
            "Dungapur" ,
            "Durg" ,
            "East Champaran" ,
            "East Delhi" ,
            "East Garo Hills" ,
            "East Khasi Hills" ,
            "East Siang" ,
            "East Sikkim" ,
            "East Singhbhum" ,
            "Eluru" ,
            "Ernakulam" ,
            "Erode" ,
            "Etah" ,
            "Etawah" ,
            "Faizabad" ,
            "Faridabad" ,
            "Faridkot" ,
            "Farrukhabad" ,
            "Fatehabad" ,
            "Fatehgarh Sahib" ,
            "Fatehpur" ,
            "Fazilka" ,
            "Firozabad" ,
            "Firozpur" ,
            "Gadag" ,
            "Gadchiroli" ,
            "Gajapati" ,
            "Ganderbal" ,
            "Gandhinagar" ,
            "Ganganagar" ,
            "Ganjam" ,
            "Garhwa" ,
            "Gautam Buddh Nagar" ,
            "Gaya" ,
            "Ghaziabad" ,
            "Ghazipur" ,
            "Giridih" ,
            "Goalpara" ,
            "Godda" ,
            "Golaghat" ,
            "Gonda" ,
            "Gondia" ,
            "Gopalganj" ,
            "Gorakhpur" ,
            "Gulbarga" ,
            "Gumla" ,
            "Guna" ,
            "Guntur" ,
            "Gurdaspur" ,
            "Gurgaon" ,
            "Gwalior" ,
            "Hailakandi" ,
            "Hamirpur" ,
            "Hamirpur" ,
            "Hanumangarh" ,
            "Harda" ,
            "Hardoi" ,
            "Haridwar" ,
            "Hassan" ,
            "Haveri district" ,
            "Hazaribag" ,
            "Hingoli" ,
            "Hissar" ,
            "Hooghly" ,
            "Hoshangabad" ,
            "Hoshiarpur" ,
            "Howrah" ,
            "Hyderabad" ,
            "Hyderabad" ,
            "Idukki" ,
            "Imphal East" ,
            "Imphal West" ,
            "Indore" ,
            "Jabalpur" ,
            "Jagatsinghpur" ,
            "Jaintia Hills" ,
            "Jaipur" ,
            "Jaisalmer" ,
            "Jajpur" ,
            "Jalandhar" ,
            "Jalaun" ,
            "Jalgaon" ,
            "Jalna" ,
            "Jalore" ,
            "Jalpaiguri" ,
            "Jammu" ,
            "Jamnagar" ,
            "Jamtara" ,
            "Jamui" ,
            "Janjgir-Champa" ,
            "Jashpur" ,
            "Jaunpur district" ,
            "Jehanabad" ,
            "Jhabua" ,
            "Jhajjar" ,
            "Jhalawar" ,
            "Jhansi" ,
            "Jharsuguda" ,
            "Jhunjhunu" ,
            "Jind" ,
            "Jodhpur" ,
            "Jorhat" ,
            "Junagadh" ,
            "Jyotiba Phule Nagar" ,
            "Kabirdham (formerly Kawardha)" ,
            "Kadapa" ,
            "Kaimur" ,
            "Kaithal" ,
            "Kakinada" ,
            "Kalahandi" ,
            "Kamrup" ,
            "Kamrup Metropolitan" ,
            "Kanchipuram" ,
            "Kandhamal" ,
            "Kangra" ,
            "Kanker" ,
            "Kannauj" ,
            "Kannur" ,
            "Kanpur" ,
            "Kanshi Ram Nagar" ,
            "Kanyakumari" ,
            "Kapurthala" ,
            "Karaikal" ,
            "Karauli" ,
            "Karbi Anglong" ,
            "Kargil" ,
            "Karimganj" ,
            "Karimnagar" ,
            "Karnal" ,
            "Karur" ,
            "Kasaragod" ,
            "Kathua" ,
            "Katihar" ,
            "Katni" ,
            "Kaushambi" ,
            "Kendrapara" ,
            "Kendujhar (Keonjhar)" ,
            "Khagaria" ,
            "Khammam" ,
            "Khandwa (East Nimar)" ,
            "Khargone (West Nimar)" ,
            "Kheda" ,
            "Khordha" ,
            "Khowai" ,
            "Khunti" ,
            "Kinnaur" ,
            "Kishanganj" ,
            "Kishtwar" ,
            "Kodagu" ,
            "Koderma" ,
            "Kohima" ,
            "Kokrajhar" ,
            "Kolar" ,
            "Kolasib" ,
            "Kolhapur" ,
            "Kolkata" ,
            "Kollam" ,
            "Koppal" ,
            "Koraput" ,
            "Korba" ,
            "Koriya" ,
            "Kota" ,
            "Kottayam" ,
            "Kozhikode" ,
            "Krishna" ,
            "Kulgam" ,
            "Kullu" ,
            "Kupwara" ,
            "Kurnool" ,
            "Kurukshetra" ,
            "Kurung Kumey" ,
            "Kushinagar" ,
            "Kutch" ,
            "Lahaul and Spiti" ,
            "Lakhimpur" ,
            "Lakhimpur Kheri" ,
            "Lakhisarai" ,
            "Lalitpur" ,
            "Latehar" ,
            "Latur" ,
            "Lawngtlai" ,
            "Leh" ,
            "Lohardaga" ,
            "Lohit" ,
            "Lower Dibang Valley" ,
            "Lower Subansiri" ,
            "Lucknow" ,
            "Ludhiana" ,
            "Lunglei" ,
            "Madhepura" ,
            "Madhubani" ,
            "Madurai" ,
            "Mahamaya Nagar" ,
            "Maharajganj" ,
            "Mahasamund" ,
            "Mahbubnagar" ,
            "Mahe" ,
            "Mahendragarh" ,
            "Mahoba" ,
            "Mainpuri" ,
            "Malappuram" ,
            "Maldah" ,
            "Malkangiri" ,
            "Mamit" ,
            "Mandi" ,
            "Mandla" ,
            "Mandsaur" ,
            "Mandya" ,
            "Mansa" ,
            "Marigaon" ,
            "Mathura" ,
            "Mau" ,
            "Mayurbhanj" ,
            "Medak" ,
            "Meerut" ,
            "Mehsana" ,
            "Mewat" ,
            "Mirzapur" ,
            "Moga" ,
            "Mokokchung" ,
            "Mon" ,
            "Moradabad" ,
            "Morena" ,
            "Mumbai City" ,
            "Mumbai suburban" ,
            "Munger" ,
            "Murshidabad" ,
            "Muzaffarnagar" ,
            "Muzaffarpur" ,
            "Mysore" ,
            "Nabarangpur" ,
            "Nadia" ,
            "Nagaon" ,
            "Nagapattinam" ,
            "Nagaur" ,
            "Nagpur" ,
            "Nainital" ,
            "Nalanda" ,
            "Nalbari" ,
            "Nalgonda" ,
            "Namakkal" ,
            "Nanded" ,
            "Nandurbar" ,
            "Narayanpur" ,
            "Narmada" ,
            "Narsinghpur" ,
            "Nashik" ,
            "Navsari" ,
            "Nawada" ,
            "Nawanshahr" ,
            "Nayagarh" ,
            "Neemuch" ,
            "Nellore" ,
            "New Delhi" ,
            "Nilgiris" ,
            "Nizamabad" ,
            "North 24 Parganas" ,
            "North Delhi" ,
            "North East Delhi" ,
            "North Goa" ,
            "North Sikkim" ,
            "North Tripura" ,
            "North West Delhi" ,
            "Nuapada" ,
            "Ongole" ,
            "Osmanabad" ,
            "Pakur" ,
            "Palakkad" ,
            "Palamu" ,
            "Pali" ,
            "Palwal" ,
            "Panchkula" ,
            "Panchmahal" ,
            "Panchsheel Nagar district (Hapur)" ,
            "Panipat" ,
            "Panna" ,
            "Papum Pare" ,
            "Parbhani" ,
            "Paschim Medinipur" ,
            "Patan" ,
            "Pathanamthitta" ,
            "Pathankot" ,
            "Patiala" ,
            "Patna" ,
            "Pauri Garhwal" ,
            "Perambalur" ,
            "Phek" ,
            "Pilibhit" ,
            "Pithoragarh" ,
            "Pondicherry" ,
            "Poonch" ,
            "Porbandar" ,
            "Pratapgarh" ,
            "Pratapgarh" ,
            "Pudukkottai" ,
            "Pulwama" ,
            "Pune" ,
            "Purba Medinipur" ,
            "Puri" ,
            "Purnia" ,
            "Purulia" ,
            "Raebareli" ,
            "Raichur" ,
            "Raigad" ,
            "Raigarh" ,
            "Raipur" ,
            "Raisen" ,
            "Rajauri" ,
            "Rajgarh" ,
            "Rajkot" ,
            "Rajnandgaon" ,
            "Rajsamand" ,
            "Ramabai Nagar (Kanpur Dehat)" ,
            "Ramanagara" ,
            "Ramanathapuram" ,
            "Ramban" ,
            "Ramgarh" ,
            "Rampur" ,
            "Ranchi" ,
            "Ratlam" ,
            "Ratnagiri" ,
            "Rayagada" ,
            "Reasi" ,
            "Rewa" ,
            "Rewari" ,
            "Ri Bhoi" ,
            "Rohtak" ,
            "Rohtas" ,
            "Rudraprayag" ,
            "Rupnagar" ,
            "Sabarkantha" ,
            "Sagar" ,
            "Saharanpur" ,
            "Saharsa" ,
            "Sahibganj" ,
            "Saiha" ,
            "Salem" ,
            "Samastipur" ,
            "Samba" ,
            "Sambalpur" ,
            "Sangli" ,
            "Sangrur" ,
            "Sant Kabir Nagar" ,
            "Sant Ravidas Nagar" ,
            "Saran" ,
            "Satara" ,
            "Satna" ,
            "Sawai Madhopur" ,
            "Sehore" ,
            "Senapati" ,
            "Seoni" ,
            "Seraikela Kharsawan" ,
            "Serchhip" ,
            "Shahdol" ,
            "Shahjahanpur" ,
            "Shajapur" ,
            "Shamli" ,
            "Sheikhpura" ,
            "Sheohar" ,
            "Sheopur" ,
            "Shimla" ,
            "Shimoga" ,
            "Shivpuri" ,
            "Shopian" ,
            "Shravasti" ,
            "Sibsagar" ,
            "Siddharthnagar" ,
            "Sidhi" ,
            "Sikar" ,
            "Simdega" ,
            "Sindhudurg" ,
            "Singrauli" ,
            "Sirmaur" ,
            "Sirohi" ,
            "Sirsa" ,
            "Sitamarhi" ,
            "Sitapur" ,
            "Sivaganga" ,
            "Siwan" ,
            "Solan" ,
            "Solapur" ,
            "Sonbhadra" ,
            "Sonipat" ,
            "Sonitpur" ,
            "South 24 Parganas" ,
            "South Delhi" ,
            "South Garo Hills" ,
            "South Goa" ,
            "South Sikkim" ,
            "South Tripura" ,
            "South West Delhi" ,
            "Sri Muktsar Sahib" ,
            "Srikakulam" ,
            "Srinagar" ,
            "Subarnapur (Sonepur)" ,
            "Sultanpur" ,
            "Sundergarh" ,
            "Supaul" ,
            "Surat" ,
            "Surendranagar" ,
            "Surguja" ,
            "Tamenglong" ,
            "Tarn Taran" ,
            "Tawang" ,
            "Tehri Garhwal" ,
            "Thane" ,
            "Thanjavur" ,
            "The Dangs" ,
            "Theni" ,
            "Thiruvananthapuram" ,
            "Thoothukudi" ,
            "Thoubal" ,
            "Thrissur" ,
            "Tikamgarh" ,
            "Tinsukia" ,
            "Tirap" ,
            "Tiruchirappalli" ,
            "Tirunelveli" ,
            "Tirupur" ,
            "Tiruvallur" ,
            "Tiruvannamalai" ,
            "Tiruvarur" ,
            "Tonk" ,
            "Tuensang" ,
            "Tumkur" ,
            "Udaipur" ,
            "Udalguri" ,
            "Udham Singh Nagar" ,
            "Udhampur" ,
            "Udupi" ,
            "Ujjain" ,
            "Ukhrul" ,
            "Umaria" ,
            "Una" ,
            "Unnao" ,
            "Upper Siang" ,
            "Upper Subansiri" ,
            "Uttar Dinajpur" ,
            "Uttara Kannada" ,
            "Uttarkashi" ,
            "Vadodara" ,
            "Vaishali" ,
            "Valsad" ,
            "Varanasi" ,
            "Vellore" ,
            "Vidisha" ,
            "Viluppuram" ,
            "Virudhunagar" ,
            "Visakhapatnam" ,
            "Vizianagaram" ,
            "Vyara" ,
            "Warangal" ,
            "Wardha" ,
            "Washim" ,
            "Wayanad" ,
            "West Champaran" ,
            "West Delhi" ,
            "West Garo Hills" ,
            "West Kameng" ,
            "West Khasi Hills" ,
            "West Siang" ,
            "West Sikkim" ,
            "West Singhbhum" ,
            "West Tripura" ,
            "Wokha" ,
            "Yadgir" ,
            "Yamuna Nagar" ,
            "Yanam" ,
            "Yavatmal" ,
            "Zunheboto"};
    private String userID;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    AutoCompleteTextView city;
    MultiAutoCompleteTextView cities_operation;
    EditText name,address,number,email,bname;
    Button change,submit;
    int PICK_IMAGE_REQUEST = 111;
    ImageView iv;
    Uri filePath;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReferenceFromUrl("gs://solaranalyser-67172.appspot.com");
    ProgressDialog pd;
    DatabaseReference dbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();


        initialise();




        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,names);
        //Getting the instance of AutoCompleteTextView
        cities_operation.setAdapter(adapter);
        cities_operation.setThreshold(1);
        cities_operation.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        city.setThreshold(1);//will start working from first character
        city.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView



        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
            }
        });


    }
public void skip_click(View v)
{
    startActivity(new Intent(this,ToDoSeller.class));
}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();

            try {
                //getting image from gallery
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                //Setting image to ImageView
                iv.setImageBitmap(bitmap);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }


    private void initialise() {
        city=(AutoCompleteTextView)findViewById(R.id.ac);
        iv=(ImageView)findViewById(R.id.imageView2);
        cities_operation=(MultiAutoCompleteTextView)findViewById(R.id.mac);
        change=(Button)findViewById(R.id.change);
        submit=(Button)findViewById(R.id.submit);
        name=(EditText)findViewById(R.id.name);
        bname=(EditText)findViewById(R.id.bname);
        address=(EditText)findViewById(R.id.address);
        number=(EditText)findViewById(R.id.contact);
        email=(EditText)findViewById(R.id.email);
        pd = new ProgressDialog(this);
        pd.setMessage("Saving....");
        dbr= FirebaseDatabase.getInstance().getReference();


    }

    public void submit_click(View v)
    {

        String names = name.getText().toString();
        String cities = city.getText().toString();
        String co =  cities_operation.getText().toString();
        String addresses = address.getText().toString();
        String emails = email.getText().toString();
        String numbers = number.getText().toString();
        String bnames = bname.getText().toString();
        String id= FirebaseAuth.getInstance().getCurrentUser().getUid();
        if (TextUtils.isEmpty(emails)) {
            Toast.makeText(getApplicationContext(), "Enter email address", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(cities)) {
            Toast.makeText(getApplicationContext(), "Enter city", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(co)) {
            Toast.makeText(getApplicationContext(), "Enter cities where you operate", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(names)) {
            Toast.makeText(getApplicationContext(), "Enter name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(addresses)) {
            Toast.makeText(getApplicationContext(), "Enter address", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(numbers)) {
            Toast.makeText(getApplicationContext(), "Enter number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(bnames)) {
            Toast.makeText(getApplicationContext(), "Enter Business name", Toast.LENGTH_SHORT).show();
            return;
        }
        if(filePath != null) {
            pd.show();

            StorageReference childRef = storageRef.child(id);

            //uploading the image
            UploadTask uploadTask = childRef.putFile(filePath);

            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    pd.dismiss();
                    Toast.makeText(getApplicationContext(), "Data Saved", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),Description.class));
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(Exception e) {
                    pd.dismiss();
                    Toast.makeText(getApplicationContext(), "Upload Failed -> " + e, Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            Toast.makeText(getApplicationContext(), "Select an image", Toast.LENGTH_SHORT).show();
        }
        Seller s=new Seller(names,bnames,cities,co,numbers,addresses,emails);
        myRef.child("sellers").child(userID).setValue(s);
        myRef.child("sellers").child(userID).child("id").setValue(userID);
        /*dbr.child("sellers").child(id).child("name").setValue(names);
        dbr.child("sellers").child(id).child("business_name").setValue(bnames);
        dbr.child("sellers").child(id).child("city").setValue(cities);
        dbr.child("sellers").child(id).child("cities_of_operation").setValue(co);
        dbr.child("sellers").child(id).child("phone").setValue(numbers);
        dbr.child("sellers").child(id).child("email").setValue(emails);*/

    }
}
