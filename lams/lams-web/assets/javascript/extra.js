$(document).ready(function() {
    $('#extraLink').on("click", function(e) {
        e.preventDefault();
        var collectXML = '%3Cbill-collect%3E%0A++%3CserviceCode%3EPT%3C%2FserviceCode%3E%0A++%3CfundCode%3E01%3C%2FfundCode%3E%0A++%3CfunctionaryCode%3E1%3C%2FfunctionaryCode%3E%0A++%3CfundSourceCode%3E01%3C%2FfundSourceCode%3E%0A++%3CdepartmentCode%3EREV%3C%2FdepartmentCode%3E%0A++%3CdisplayMessage%3EProperty+Tax+Collection%3C%2FdisplayMessage%3E%0A++%3CpaidBy%3ENageshwara+Rao%3C%2FpaidBy%3E%0A++%3CpartPaymentAllowed%3E1%3C%2FpartPaymentAllowed%3E%0A++%3CcallbackForApportioning%3E1%3C%2FcallbackForApportioning%3E%0A++%3CoverrideAccountHeadsAllowed%3E0%3C%2FoverrideAccountHeadsAllowed%3E%0A++%3CcollectionType%3EF%3C%2FcollectionType%3E%0A++%3CcollectionModeNotAllowed%3E%3C%2FcollectionModeNotAllowed%3E%0A++%3Cpayees%3E%0A++++%3Cpayee%3E%0A++++++%3CpayeeName%3ENageshwara+Rao%3C%2FpayeeName%3E%0A++++++%3CpayeeAddress%3E87%2F1091%2C+4th+colony%2C+Kurnool%2C+%3C%2FpayeeAddress%3E%0A++++++%3CpayeeEmail%3E%3C%2FpayeeEmail%3E%0A++++++%3Cbills%3E%0A++++++++%3Cbill+refNo%3D%22694088%22+billDate%3D%2221%2F03%2F2017%22+consumerCode%3D%221016043379%22%3E%0A++++++++++%3CconsumerType%3EPrivate%3C%2FconsumerType%3E%0A++++++++++%3CboundaryNum%3E9999%3C%2FboundaryNum%3E%0A++++++++++%3CboundaryType%3EWard%3C%2FboundaryType%3E%0A++++++++++%3Cdescription%3EProperty+Tax+Assessment+Number%3A+1016043379%3C%2Fdescription%3E%0A++++++++++%3CtotalAmount%3E1390%3C%2FtotalAmount%3E%0A++++++++++%3CminimumAmount%3E0%3C%2FminimumAmount%3E%0A++++++++++%3Caccounts%3E%0A++++++++++++%3Caccount+glCode%3D%223504102%22+order%3D%229%22+description%3D%22Advance-2018-2019-1%22+isActualDemand%3D%220%22+purpose%3D%22ADVANCE_AMOUNT%22%3E%0A++++++++++++++%3CcrAmount%3E1222.0%3C%2FcrAmount%3E%0A++++++++++++++%3CdrAmount%3E0%3C%2FdrAmount%3E%0A++++++++++++++%3CfunctionCode%3E9100%3C%2FfunctionCode%3E%0A++++++++++++%3C%2Faccount%3E%0A++++++++++++%3Caccount+glCode%3D%221100101%22+order%3D%221%22+description%3D%22General+Tax-2016-2017-1%22+isActualDemand%3D%221%22+purpose%3D%22CURRENT_AMOUNT%22%3E%0A++++++++++++++%3CcrAmount%3E9.0%3C%2FcrAmount%3E%0A++++++++++++++%3CdrAmount%3E0%3C%2FdrAmount%3E%0A++++++++++++++%3CfunctionCode%3E9100%3C%2FfunctionCode%3E%0A++++++++++++%3C%2Faccount%3E%0A++++++++++++%3Caccount+glCode%3D%223503001%22+order%3D%225%22+description%3D%22Library+Cess-2016-2017-2%22+isActualDemand%3D%221%22+purpose%3D%22CURRENT_AMOUNT%22%3E%0A++++++++++++++%3CcrAmount%3E85.0%3C%2FcrAmount%3E%0A++++++++++++++%3CdrAmount%3E0%3C%2FdrAmount%3E%0A++++++++++++++%3CfunctionCode%3E9100%3C%2FfunctionCode%3E%0A++++++++++++%3C%2Faccount%3E%0A++++++++++++%3Caccount+glCode%3D%223504102%22+order%3D%2216%22+description%3D%22Advance-2021-2022-2%22+isActualDemand%3D%220%22+purpose%3D%22ADVANCE_AMOUNT%22%3E%0A++++++++++++++%3CcrAmount%3E1222.0%3C%2FcrAmount%3E%0A++++++++++++++%3CdrAmount%3E0%3C%2FdrAmount%3E%0A++++++++++++++%3CfunctionCode%3E9100%3C%2FfunctionCode%3E%0A++++++++++++%3C%2Faccount%3E%0A++++++++++++%3Caccount+glCode%3D%223503002%22+order%3D%223%22+description%3D%22Education+Cess-2016-2017-1%22+isActualDemand%3D%221%22+purpose%3D%22CURRENT_AMOUNT%22%3E%0A++++++++++++++%3CcrAmount%3E194.0%3C%2FcrAmount%3E%0A++++++++++++++%3CdrAmount%3E0%3C%2FdrAmount%3E%0A++++++++++++++%3CfunctionCode%3E9100%3C%2FfunctionCode%3E%0A++++++++++++%3C%2Faccount%3E%0A++++++++++++%3Caccount+glCode%3D%221100101%22+order%3D%224%22+description%3D%22General+Tax-2016-2017-2%22+isActualDemand%3D%221%22+purpose%3D%22CURRENT_AMOUNT%22%3E%0A++++++++++++++%3CcrAmount%3E823.0%3C%2FcrAmount%3E%0A++++++++++++++%3CdrAmount%3E0%3C%2FdrAmount%3E%0A++++++++++++++%3CfunctionCode%3E9100%3C%2FfunctionCode%3E%0A++++++++++++%3C%2Faccount%3E%0A++++++++++++%3Caccount+glCode%3D%223504102%22+order%3D%227%22+description%3D%22Advance-2017-2018-1%22+isActualDemand%3D%220%22+purpose%3D%22ADVANCE_AMOUNT%22%3E%0A++++++++++++++%3CcrAmount%3E1222.0%3C%2FcrAmount%3E%0A++++++++++++++%3CdrAmount%3E0%3C%2FdrAmount%3E%0A++++++++++++++%3CfunctionCode%3E9100%3C%2FfunctionCode%3E%0A++++++++++++%3C%2Faccount%3E%0A++++++++++++%3Caccount+glCode%3D%223504102%22+order%3D%2213%22+description%3D%22Advance-2020-2021-1%22+isActualDemand%3D%220%22+purpose%3D%22ADVANCE_AMOUNT%22%3E%0A++++++++++++++%3CcrAmount%3E1222.0%3C%2FcrAmount%3E%0A++++++++++++++%3CdrAmount%3E0%3C%2FdrAmount%3E%0A++++++++++++++%3CfunctionCode%3E9100%3C%2FfunctionCode%3E%0A++++++++++++%3C%2Faccount%3E%0A++++++++++++%3Caccount+glCode%3D%223504102%22+order%3D%2214%22+description%3D%22Advance-2020-2021-2%22+isActualDemand%3D%220%22+purpose%3D%22ADVANCE_AMOUNT%22%3E%0A++++++++++++++%3CcrAmount%3E1222.0%3C%2FcrAmount%3E%0A++++++++++++++%3CdrAmount%3E0%3C%2FdrAmount%3E%0A++++++++++++++%3CfunctionCode%3E9100%3C%2FfunctionCode%3E%0A++++++++++++%3C%2Faccount%3E%0A++++++++++++%3Caccount+glCode%3D%223504102%22+order%3D%2215%22+description%3D%22Advance-2021-2022-1%22+isActualDemand%3D%220%22+purpose%3D%22ADVANCE_AMOUNT%22%3E%0A++++++++++++++%3CcrAmount%3E1222.0%3C%2FcrAmount%3E%0A++++++++++++++%3CdrAmount%3E0%3C%2FdrAmount%3E%0A++++++++++++++%3CfunctionCode%3E9100%3C%2FfunctionCode%3E%0A++++++++++++%3C%2Faccount%3E%0A++++++++++++%3Caccount+glCode%3D%223503001%22+order%3D%222%22+description%3D%22Library+Cess-2016-2017-1%22+isActualDemand%3D%221%22+purpose%3D%22CURRENT_AMOUNT%22%3E%0A++++++++++++++%3CcrAmount%3E85.0%3C%2FcrAmount%3E%0A++++++++++++++%3CdrAmount%3E0%3C%2FdrAmount%3E%0A++++++++++++++%3CfunctionCode%3E9100%3C%2FfunctionCode%3E%0A++++++++++++%3C%2Faccount%3E%0A++++++++++++%3Caccount+glCode%3D%223504102%22+order%3D%228%22+description%3D%22Advance-2017-2018-2%22+isActualDemand%3D%220%22+purpose%3D%22ADVANCE_AMOUNT%22%3E%0A++++++++++++++%3CcrAmount%3E1222.0%3C%2FcrAmount%3E%0A++++++++++++++%3CdrAmount%3E0%3C%2FdrAmount%3E%0A++++++++++++++%3CfunctionCode%3E9100%3C%2FfunctionCode%3E%0A++++++++++++%3C%2Faccount%3E%0A++++++++++++%3Caccount+glCode%3D%223503002%22+order%3D%226%22+description%3D%22Education+Cess-2016-2017-2%22+isActualDemand%3D%221%22+purpose%3D%22CURRENT_AMOUNT%22%3E%0A++++++++++++++%3CcrAmount%3E194.0%3C%2FcrAmount%3E%0A++++++++++++++%3CdrAmount%3E0%3C%2FdrAmount%3E%0A++++++++++++++%3CfunctionCode%3E9100%3C%2FfunctionCode%3E%0A++++++++++++%3C%2Faccount%3E%0A++++++++++++%3Caccount+glCode%3D%223504102%22+order%3D%2210%22+description%3D%22Advance-2018-2019-2%22+isActualDemand%3D%220%22+purpose%3D%22ADVANCE_AMOUNT%22%3E%0A++++++++++++++%3CcrAmount%3E1222.0%3C%2FcrAmount%3E%0A++++++++++++++%3CdrAmount%3E0%3C%2FdrAmount%3E%0A++++++++++++++%3CfunctionCode%3E9100%3C%2FfunctionCode%3E%0A++++++++++++%3C%2Faccount%3E%0A++++++++++++%3Caccount+glCode%3D%223504102%22+order%3D%2211%22+description%3D%22Advance-2019-2020-1%22+isActualDemand%3D%220%22+purpose%3D%22ADVANCE_AMOUNT%22%3E%0A++++++++++++++%3CcrAmount%3E1222.0%3C%2FcrAmount%3E%0A++++++++++++++%3CdrAmount%3E0%3C%2FdrAmount%3E%0A++++++++++++++%3CfunctionCode%3E9100%3C%2FfunctionCode%3E%0A++++++++++++%3C%2Faccount%3E%0A++++++++++++%3Caccount+glCode%3D%223504102%22+order%3D%2212%22+description%3D%22Advance-2019-2020-2%22+isActualDemand%3D%220%22+purpose%3D%22ADVANCE_AMOUNT%22%3E%0A++++++++++++++%3CcrAmount%3E1222.0%3C%2FcrAmount%3E%0A++++++++++++++%3CdrAmount%3E0%3C%2FdrAmount%3E%0A++++++++++++++%3CfunctionCode%3E9100%3C%2FfunctionCode%3E%0A++++++++++++%3C%2Faccount%3E%0A++++++++++%3C%2Faccounts%3E%0A++++++++%3C%2Fbill%3E%0A++++++%3C%2Fbills%3E%0A++++%3C%2Fpayee%3E%0A++%3C%2Fpayees%3E%0A%3C%2Fbill-collect%3E';
        var infoMessage = '<s:property value="%{infoMessage}" />';
        //if(infoMessage !=null && infoMessage.length>0) 
        //    alert(infoMessage);
        jQuery('<form>.').attr({
            method: 'post',
            action: 'http://172.16.2.233:9880/collection/receipts/receipt-newform.action',
            target: '_self'
        }).append(jQuery('<input>').attr({
            type: 'hidden',
            id: 'collectXML',
            name: 'collectXML',
            value: collectXML
        })).appendTo( document.body ).submit();
    })
})