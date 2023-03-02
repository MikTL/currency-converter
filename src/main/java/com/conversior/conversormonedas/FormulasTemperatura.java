package com.conversior.conversormonedas;
public class FormulasTemperatura {
    private Double celsius;
    private Double kelvin;
    private Double fahrenheit;
    private Double rankine;

    public String[] celsius(Double valor){
        this.celsius = valor;
        this.kelvin = Math.round((valor +273.15)*100.0)/100.0;
        this.fahrenheit = Math.round(((valor*(9.0/5.0))+32.0)*100.0)/100.0;
        this.rankine = Math.round(((valor+273.15)*(9.0/5.0))*100.)/100.0;
        return new String[]{String.valueOf(this.celsius), String.valueOf(this.kelvin), String.valueOf(this.fahrenheit), String.valueOf(this.rankine)};
    }
    public String[] kelvin(Double valor){
        this.celsius =Math.round((valor - 273.15)*100.0)/100.0;
        this.kelvin = valor;
        this.fahrenheit = Math.round(((valor - 273.15) * (9.0 / 5.0) + 32.0)*100.0)/100.0;
        this.rankine = Math.round((valor * (9.0 / 5.0))*100.0)/100.0;
        return new String[]{String.valueOf(this.celsius), String.valueOf(this.kelvin), String.valueOf(this.fahrenheit), String.valueOf(this.rankine)};
    }
    public String[] fahrenheit(Double valor){
        this.celsius = Math.round(((valor-32.0)*(5.0/9.0))*100.0)/100.0;
        this.kelvin = Math.round(((valor+459.67)*(5.0/9.0))*100.0)/100.0;
        this.fahrenheit = valor;
        this.rankine =Math.round((valor+459.67)*100.0)/100.0;
        return new String[]{String.valueOf(this.celsius), String.valueOf(this.kelvin), String.valueOf(this.fahrenheit), String.valueOf(this.rankine)};
    }
    public String[] rankine(Double valor){
        this.celsius = Math.round(((valor*(5.0/9.0))-273.15)*100.0)/100.0;
        this.kelvin = Math.round((valor*(5.0/9.0))*100.0)/100.0;
        this.fahrenheit = Math.round((valor-459.67)*100.0)/100.0;
        this.rankine =valor;
        return new String[]{String.valueOf(this.celsius), String.valueOf(this.kelvin), String.valueOf(this.fahrenheit), String.valueOf(this.rankine)};
    }
}
