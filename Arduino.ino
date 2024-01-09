
#include "DHT.h"
DHT dht1(6, DHT22); // Capteur DHT22 à l'intérieur du réfrigérateur
DHT dht2(8, DHT22); // Capteur DHT22 à l'extérieur du réfrigérateur

// Constante mesuree avec un thermometre de reference
// qui sert a etalonner le capteur de temperature
#define DELTA_TEMPERATURE 0.7

int consigne = 0;
String receivedString = "";
int receivedConsigne = 0;
float seuilTemperature = 0;
float dewPoint;          // Point de rosée


void setup() {
  dht1.begin();
  dht2.begin();
  Serial.begin(9600);
  pinMode(3, OUTPUT);

}

void loop() {
  float tauxHumiditeInterieur = dht1.readHumidity();
  float temperatureInterieur = (dht1.readTemperature() - DELTA_TEMPERATURE);

  // Calcul du point de rosée
  double a = 17.27;
  double b = 237.7;
  double temp = (a * temperatureInterieur) / (b + temperatureInterieur) + log(tauxHumiditeInterieur/100.0);
  dewPoint = (b * temp) / (a - temp);


  float tauxHumiditeExterieur = dht2.readHumidity();
  float temperatureExterieur = (dht2.readTemperature()  - DELTA_TEMPERATURE);
  float seuilTemperature = (temperatureExterieur + temperatureInterieur)/2;

  // Envoi des données via le port série
  Serial.print(tauxHumiditeInterieur);
  Serial.print(",");
  Serial.print(temperatureInterieur);
  Serial.print(",");
  Serial.print(temperatureExterieur);
  Serial.print(",");
  Serial.print(dewPoint);
  Serial.println();

    // Read the setpoint from the serial port
    if (Serial.available() > 0) {
        String receivedString = Serial.readStringUntil('\n');
        receivedConsigne = receivedString.toInt();
        // Update the consigne variable with the received value
        consigne = receivedConsigne;
        Serial.println(consigne);
    }
  // Pour l'activation du module peltier
  if (temperatureInterieur > consigne) {
    digitalWrite(3, HIGH);
    //Serial.println("Activation Module Peltier");
  } else {
    digitalWrite(3, LOW);
    //Serial.println("Module Peltier Désactivé");
  }

  // Vérifier s'il y a risque de condensation
  if (temperatureInterieur <= dewPoint) {
    //Serial.println("Risque de condensation !");
    // Ajoutez ici le code pour déclencher une alerte, par exemple un signal sonore ou une notification
  }

  // Vérifier s'il y a une augmentation anormale de la température
  if (temperatureInterieur > seuilTemperature) {
    //Serial.println("Alerte : Temperature élevée !");
    // Ajoutez ici le code pour déclencher une alerte spécifique, par exemple un signal sonore ou une notification
  }

  // Temporisation
  delay(2000);
}
