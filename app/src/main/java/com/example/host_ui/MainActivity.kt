package com.example.host_ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.uqpay.sdk.hostUI.UQPayHostUI
import com.uqpay.sdk.hostUI.model.BankCardBean
import com.uqpay.sdk.hostUI.utils.UQModelEnum


class MainActivity : AppCompatActivity() {

    private lateinit var cardNo:TextView
    private lateinit var cardIssuer:TextView
    private lateinit var cardUUID:TextView
    private lateinit var hostUI: UQPayHostUI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cardNo = findViewById(R.id.cardNo)
        cardIssuer = findViewById(R.id.cardIssuer)
        cardUUID = findViewById(R.id.cardUUID)
        hostUI = UQPayHostUI(this)
        hostUI.setModelEnum(UQModelEnum.TEST)
    }
    fun openHostUI(view: View) {
        /**
         * the token is valid for 3 hours
         * this token generate by UQPAY Servier Side SDK
         */
        val token = "a4086ff846da421f958ed6327b79c37e"
        hostUI.start(token)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == UQPayHostUI.SELECT_CARD_REQUEST_CODE ) { // TODO later we will update just only one request code
                val cardBean:BankCardBean = data!!.getParcelableExtra(UQPayHostUI.SELECT_CARD_REQUEST)
                cardNo.text = cardBean.panTail
                cardIssuer.text = cardBean.issuer
                cardUUID.text = cardBean.uuid // use this uuid send to UQPAY Payment Server by our Server Side SDK with your Order Data
            } else if (requestCode == UQPayHostUI.ADD_CARD_RESULT_CODE) { // TODO will remove soon
                val cardBean:BankCardBean = data!!.getParcelableExtra(UQPayHostUI.SELECT_CARD_REQUEST)
                cardNo.text = cardBean.panTail
                cardIssuer.text = cardBean.issuer
                cardUUID.text = cardBean.uuid
            }
        }
    }

}
