package com.example.weatherapp;

public class WeatherResponse {
    private Main main;
    private Wind wind;
    private Weather[] weather;

    public Main getMain() {
        return main;
    }
    public Wind getWind() {
        return wind;
    }
    public Weather[] getWeather() {
        return weather;
    }

    public class Main {
        private double temp;
        private double feels_like;

        public double getTemp() {
            return temp;
        }
        public double getFeelsLike() {
            return feels_like;
        }
    }

    public class Wind {
        private double speed;

        public double getSpeed() {
            return speed;
        }
    }

    public class Weather {
        private String main;
        private String description;

        public String getMain() {
            return main;
        }
        public String getDescription() {
            return description;
        }
    }
}