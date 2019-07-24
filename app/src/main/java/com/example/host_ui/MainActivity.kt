package com.example.host_ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.uqpay.sdk.hostUI.UQPayHostUI
import com.uqpay.sdk.hostUI.api.BankCardResult
import com.uqpay.sdk.hostUI.utils.EnvEnum


class MainActivity : AppCompatActivity() {

    private lateinit var cardNo:TextView
    private lateinit var cardIssuer:TextView
    private lateinit var cardUUID:TextView
    private lateinit var hostUI: UQPayHostUI

    private val callUQpayHostUI = 666

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cardNo = findViewById(R.id.cardNo)
        cardIssuer = findViewById(R.id.cardIssuer)
        cardUUID = findViewById(R.id.cardUUID)
        hostUI = UQPayHostUI().env(EnvEnum.TEST)
    }
    fun openHostUI(view: View) {
        /**
         * the token is valid for 3 hours
         * this token generate by UQPAY Servier Side SDK
         */
        val token = "5b114ab49227446a98a0b6ac6e50eee0"
        hostUI.token(token)
        startActivityForResult(hostUI.getIntentForBottomDialog(this), callUQpayHostUI)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == callUQpayHostUI ) {
                val cardBean:BankCardResult = data!!.getParcelableExtra(UQPayHostUI.UQPAY_CARD_SELECTED)
                cardNo.text = cardBean.panTail
                cardIssuer.text = cardBean.issuer
                cardUUID.text = cardBean.uuid // use this uuid send to UQPAY Payment Server by our Server Side SDK with your Order Data
            }
        }
    }

}
