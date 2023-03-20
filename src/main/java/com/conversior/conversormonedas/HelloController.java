package com.conversior.conversormonedas;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private ChoiceBox<String> queConvertir;
    @FXML
    private Label mainLabel;
    @FXML
    private Label labelResultado;

    @FXML
    TextInputDialog valorInput = new TextInputDialog("Ingrese el monto");
    public double monto = 0;
    ValoresMonedas valoresMonedas = new ValoresMonedas();
    FormulasTemperatura formT = new FormulasTemperatura();
    String[] paresMonedas = {"Sol/Dolar","Sol/Euro","Sol/Libra Esterlina","Sol/Yen Japonés","Sol/Won Surcoreano"};
    String[] UMTemperatura = {"Celsius","Kelvin","Fahrenheit","Rankine"};
    String textLabelTemp = "Selecciona el par de temperaturas";
    String valueLabelTemp = "Pares temperaturas";
    String textLabelMon = "Selecciona el par de monedas";
    String valueLabelMon = "Pares monedas";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainLabel.setText("Seleccione una opción de Conversión");

        queConvertir.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue.equals("Convertir Monedas")) {
                ValoresInput("Hola","Ingrese un Monto","Valor");
                validarInput(valorInput,0.0,textLabelMon,paresMonedas,valueLabelMon);
                escuchadorMoneda(paresMonedas);
            } else if (newValue.equals("Convertir Temperatura")) {
                ValoresInput("Hola","Ingrese una temperatura","Valor");
                validarInput(valorInput,-459.67,textLabelTemp,UMTemperatura,valueLabelTemp);
                queConvertir.getSelectionModel().selectedItemProperty().addListener((valorObservable,valorAntiguo,valorNuevo)->{
                    if(valorNuevo.equals(UMTemperatura[0])){
                        resTemp(formT,monto,"Celsius");
                    } else if (valorNuevo.equals(UMTemperatura[1])) {
                        resTemp(formT,monto,"Kelvin");
                    } else if (valorNuevo.equals(UMTemperatura[2])) {
                        resTemp(formT,monto,"Fahrenheit");
                    } else if (valorNuevo.equals(UMTemperatura[3])) {
                        resTemp(formT,monto,"Rankine");
                    }
                });
            }
        });
    }
    public void actualizarIntefaz(String textLabel,String[] pares,String value){
        mainLabel.setText(textLabel);
        queConvertir.setValue(value);
        queConvertir.getItems().clear();
        queConvertir.getItems().addAll(pares);
    }
    public void ValoresInput(String titulo, String textoHeader,String contenidoTexto){
        valorInput.setTitle(titulo);
        valorInput.setHeaderText(textoHeader);
        valorInput.setContentText(contenidoTexto);
    }
    public void setResultadoMonedas(Double monto, Double vm, String moneda){
        labelResultado.setText(
                monto+" Soles son "+ Math.round ((monto/ vm)*10.0)/10.0 +" "+moneda+
                        "\n"+monto+" "+ moneda+" son "+Math.round((monto* vm)*10.0)/10.0 +" Soles"
        );
    }
    public void resTemp(FormulasTemperatura formTemp ,Double valor,String origen){
        String uno;
        String dos;
        String tres;

        int indice0;
        int indice1;
        int indice2;
        int indice3;
        if(origen=="Celsius"){
            uno = "Kelvin";
            dos = " °Fahrenheit";
            tres = "° Rankine";
            indice0 = 0;
            indice1 = 1;
            indice2 = 2;
            indice3 = 3;
            String[] res= formTemp.celsius(valor);
            setResTemp(res,origen,uno,dos,tres,indice0,indice1,indice2,indice3);
        } else if (origen=="Kelvin") {
            uno = "° Celsius";
            dos = "° Fahrenheit";
            tres = "° Rankine";
            indice0 = 1;
            indice1 = 0;
            indice2 = 2;
            indice3 = 3;
            String[] res=formTemp.kelvin(valor);
            setResTemp(res,origen,uno,dos,tres,indice0,indice1,indice2,indice3);
        } else if (origen=="Fahrenheit") {
            uno = "° Celsius";
            dos = "Kelvin";
            tres = "° Rankine";
            indice0 = 2;
            indice1 = 0;
            indice2 = 1;
            indice3 = 3;
            String[] res=formTemp.fahrenheit(valor);
            setResTemp(res,origen,uno,dos,tres,indice0,indice1,indice2,indice3);
        } else if (origen=="Rankine") {
            uno = "° Celsius";
            dos = "Kelvin";
            tres = "° Fahrenheit";
            indice0 = 3;
            indice1 = 0;
            indice2 = 1;
            indice3 = 2;
            String[] res=formTemp.rankine(valor);
            setResTemp(res,origen,uno,dos,tres,indice0,indice1,indice2,indice3);
        }


    }
    public void setResTemp(String[] res,String origen, String uno,String dos, String tres,int indice0,int indice1,int indice2,int indice3){
        if (res[0] != "") {
            labelResultado.setText(
                    res[indice0]+"° "+origen+" son "+res[indice1]+uno+"\n"+
                            res[indice0]+"° "+origen+" son "+res[indice2]+dos+"\n"+
                            res[indice0]+"° "+origen+" son "+res[indice3]+tres
            );
        }else {
            labelResultado.setText("Temperatura muy baja para esta unidad de medida");
        }
    }
    public void escuchadorMoneda(String[] paresMonedas){
        queConvertir.getSelectionModel().selectedItemProperty().addListener((valorObservable,valorAntiguo,valorNuevo)->{
            if(valorNuevo.equals(paresMonedas[0])){
                setResultadoMonedas(monto,valoresMonedas.getDolar() , "Dolares");
            }else if(valorNuevo.equals(paresMonedas[1])){
                setResultadoMonedas(monto, valoresMonedas.getEuro(),"Euros");
            } else if (valorNuevo.equals(paresMonedas[2])) {
                setResultadoMonedas(monto,valoresMonedas.getGbp(),"Libras Esterlinas");
            } else if (valorNuevo.equals(paresMonedas[3])) {
                setResultadoMonedas(monto,valoresMonedas.getJpy(),"Yen Japones");
            } else if (valorNuevo.equals(paresMonedas[4])) {
                setResultadoMonedas(monto,valoresMonedas.getKrw(),"Won Sul-coreano");
            }
        });
    }
    public void validarInput(TextInputDialog input, double limite,String textLabel,String[]pares,String valueLabel){
        Optional<String> result = input.showAndWait();
        if(!result.isPresent()){
            System.out.println("El no hay valor");
        }else{
            result.ifPresent(s -> {
                try{
                    monto = Double.parseDouble(s);
                    if(monto<limite){
                        throw new NumberFormatException();
                    }
                    actualizarIntefaz(textLabel, pares,valueLabel);
                    mainLabel.setText("Selecciona un par");
                    labelResultado.setText("");
                }catch (NumberFormatException nun){
                    if(limite==0.0){
                        mainLabel.setText("Seleccione otra vez");
                        labelResultado.setText("Ingresa numeros mayores a 0");
                    }else {
                        mainLabel.setText("Seleccione otra vez");
                        labelResultado.setText("El valor minimo en la unidad de medida Fahrenheit \n es -459.67, no " +
                                "puede ingresar valores mas bajos");
                    }
                    queConvertir.setValue("Opción");
                }

            });
        }
    }

}