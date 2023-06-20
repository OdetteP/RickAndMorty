package com.example.myapplication.ui.main

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.data.models.CharacterData
import java.io.File
import java.io.FileOutputStream

//class CharacterDetailPage: Fragment() {
//
//    private val viewModel by lazy { ViewModelProvider(this).get(CharacterDetailViewModel::class.java) }
//    private val REQUEST_CODE_STORAGE_PERMISSION = 1
//    private val CHARACTER_ID = "CHARACTER_ID"
//    private val character = MutableLiveData<CharacterData>()
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
//    ): View? { val view = inflater.inflate(R.layout.fragment_character_details_page, container, false)
//        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
//
//        arguments?.let { arguments -> viewModel.refreshData(characterId = arguments.getInt(CHARACTER_ID)) }
//
//        viewModel.characters.observe(viewLifecycleOwner, Observer { characterData ->
//            Picasso.get().load(characterData.image).into(image)
//            view.text_view_name.text = "Name: ${characterData.name}"
//            view.text_view_status.text = "Status: ${characterData.status}"
//            view.text_view_species.text = "Species: ${characterData.species}"
//            view.text_view_origin.text = "Origin: ${characterData.origin.name}"
//
////            button_transport_file.setOnClickListener {
////                if (ContextCompat.checkSelfPermission(requireContext(),
////                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
////                    != PackageManager.PERMISSION_GRANTED) {
////                    ActivityCompat.requestPermissions(
////                        context as Activity,
////                        arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
////                        REQUEST_CODE_STORAGE_PERMISSION
////                    )
////                } else{
////                    exportCharacterDetails()
////                }
////            }
//        })
//
//        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
//            Toast.makeText(context, "Error: $it", Toast.LENGTH_LONG).show()
//        })
//
//        return view
//    }
//
////    private fun exportCharacterDetails() {
////        try {
////            val file = File(Environment.getExternalStorageDirectory(), "character_details.txt")
////            val outputStream = FileOutputStream(file)
////
////            val data = "Name: ${character.value?.name}\nStatus: ${character.value?.status}" +
////                    "\nSpecies: ${character.value?.species}\nOrigin: ${character.value?.origin?.name}"
////            outputStream.write(data.toByteArray())
////            outputStream.close()
////
////            Toast.makeText(context, "File exported to ${file.absolutePath}", Toast.LENGTH_LONG).show()
////            // This text could come out of the strings.xml file when working with a big project
////        } catch (e: Exception) {
////            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
////        }
////    }
////
////    @Deprecated("Deprecated in Java")
////    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
////        if (requestCode == REQUEST_CODE_STORAGE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
////            exportCharacterDetails()
////        } else {
////            Toast.makeText(
////                context,
////                "Storage permission is required to export the file", Toast.LENGTH_LONG
////            // This text could come out of the strings.xml file when working with a big project
////            ).show()
////        }
////    }
//}
//
//

