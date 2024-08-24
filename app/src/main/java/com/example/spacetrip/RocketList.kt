package com.example.spacetrip

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RocketList : AppCompatActivity() {

    private lateinit var myRecyclerView: RecyclerView
    lateinit var rocketArrayList: ArrayList<Rockets>
    lateinit var databaseReference: DatabaseReference
    lateinit var content: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_rocket_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        myRecyclerView = findViewById(R.id.recyclerView)

        val rocketImageArray = arrayOf(
            R.drawable.img1,
            R.drawable.img1,
            R.drawable.img1,
            R.drawable.img1,
            R.drawable.img1,
        )

        val rocketHeadingArray = arrayOf(
            "Polar Satellite Launch Vehicle",
            "Geosynchronous Satellite Launch Vehicle",
            "Satellite Launch Vehicle-3",
            "Launch Vehicle Mark-3",
            "Augmented Satellite Launch Vehicle"
        )

        val rocketSuccessfullMissions = arrayOf(
            "Successfull Mission: 56",
            "Successfull Mission: 22",
            "Successful Missions: 3",
            "Successfull Missions: 5",
            "Successful Missions: 11"
        )
        val rocketSuccessRate = arrayOf(
            "Success Rate: 96.5%",
            "Success Rate: 81.4%",
            "Success Rate: 50%",
            "Success Rate: 71.4%",
            "Success Rate: 78.5%"
        )
        val rocketFirstLaunch = arrayOf(
            "First Launch Date: October 15, 1994",
            "First Launch Date: April 18, 2001",
            "First Launch Date: August 10, 1979",
            "First Launch Date: December 18, 2014",
            "First Launch Date: March 24, 1987"
        )

        val rocketEngineContent = arrayOf(
            "The PSLV is a four-stage rocket, with alternating solid and liquid propulsion stages: First Stage (PS1): Solid rocket motor (S139) with six strap-on solid rocket boosters. Second Stage (PS2): Liquid engine using the Vikas engine (an ISRO-developed engine based on the Viking engine of France). Third Stage (PS3): Solid rocket motor (HPS3). Fourth Stage (PS4): Liquid stage with two Earth-storable liquid engines (PS-4).",
            "Three-stage vehicle: First Stage: Solid rocket motor (S139) with four liquid strap-on boosters using Vikas engines. Second Stage: Liquid stage using the Vikas engine. Third Stage: Cryogenic Upper Stage (CUS) using liquid hydrogen (LH2) as fuel and liquid oxygen (LOX) as oxidizer.",
            "Stages: SLV-3 is a four-stage rocket. First Stage: Solid propulsion with a motor derived from the French Centaure rocket. Thrust: ~47 tonnes. Second Stage: Solid propulsion, with a motor derived from the American Nike-Apache. Thrust: ~15 tonnes. Third Stage: Solid propulsion, also derived from Nike-Apache technology. Thrust: ~7 tonnes. Fourth Stage: Solid propulsion with a lighter motor. Thrust: ~2 tonnes.",
            "Three-stage vehicle: First Stage: Solid rocket boosters (S200). Second Stage: Liquid core stage using the L110 engine (Vikas engine). Third Stage: Cryogenic Upper Stage (C25) using the CE-20 engine.",
            "Stages: ASLV is a five-stage rocket. First Two Stages: Booster rockets that are strapped onto the core vehicle. These are derived from SLV-3’s first stage. Thrust: Each booster provides ~47 tonnes. Third Stage: Core motor, also solid-fueled, similar to SLV-3’s first stage. Thrust: ~47 tonnes. Fourth Stage: Solid propulsion, derived from the SLV-3’s second stage. Thrust: ~15 tonnes. Fifth Stage: Solid propulsion, derived from the SLV-3’s third stage. Thrust: ~7 tonnes."
        )
        val rocketFuelTypes = arrayOf(
            "Solid Stages (First and Third Stages): Use hydroxyl-terminated polybutadiene (HTPB) as the fuel. Liquid Stages (Second and Fourth Stages): Use a combination of Unsymmetrical Dimethylhydrazine (UDMH) as the fuel and Nitrogen Tetroxide (N2O4) as the oxidizer.",
            "First Stage: Solid propellant (HTPB). Strap-ons and Second Stage: UDMH (fuel) and N2O4 (oxidizer). Third Stage (Cryogenic Stage): Liquid hydrogen (LH2) and liquid oxygen (LOX).",
            "All Stages: The SLV-3 used solid propellants, typically a composite propellant made of ammonium perchlorate, aluminum powder, and a polymer binder.",
            "First Stage: Solid propellant (HTPB). Second Stage: UDMH (fuel) and N2O4 (oxidizer). Third Stage: Liquid hydrogen (LH2) and liquid oxygen (LOX).",
            "All Stages: ASLV used solid propellants, similar to SLV-3, with composite propellants. The boosters also used the same type of solid-propellant."
        )

        val rocketContent = arrayOf(
            getString(R.string.rocket_content), getString(R.string.rocket_content),
            getString(R.string.rocket_content), getString(R.string.rocket_content),
            getString(R.string.rocket_content),
        )

        myRecyclerView.layoutManager = LinearLayoutManager(this)
        rocketArrayList = arrayListOf<Rockets>()

        for (index in rocketImageArray.indices) {
            val rocket = Rockets(
                rocketHeadingArray[index],
                rocketImageArray[index],
                rocketContent[index],
                rocketSuccessfullMissions[index],
                rocketSuccessRate[index],
                rocketFirstLaunch[index],
                rocketEngineContent[index],
                rocketFuelTypes[index]
            )
            rocketArrayList.add(rocket)
        }

        var myAdapter = MyAdapter(rocketArrayList, this)
        myRecyclerView.adapter = myAdapter

        myAdapter.setOnItemClickListener(object : MyAdapter.onItemClickListener {
            override fun onItemClicking(position: Int) {

                val intent = Intent(this@RocketList, RocketsDetail::class.java)
                intent.putExtra("heading", rocketArrayList[position].rocketHeading)
                intent.putExtra("imageId", rocketArrayList[position].rocketImage)
                intent.putExtra("rocketContent", rocketArrayList[position].rocketContent)
                intent.putExtra("successfulMissions", rocketArrayList[position].rocketSuccessfullMissions)
                intent.putExtra("successRate", rocketArrayList[position].rocketSuccessRate)
                intent.putExtra("firstLaunch", rocketArrayList[position].rocketFirstLaunch)
                intent.putExtra("engineContent", rocketArrayList[position].rocketEngineContent)
                intent.putExtra("fuelTypes", rocketArrayList[position].rocketFuelTypes)

                startActivity(intent)
            }

        })
    }



}
