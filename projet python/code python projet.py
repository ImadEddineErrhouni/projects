import pandas as pd
import matplotlib.pyplot as plt

# Chemin vers ton fichier CSV
path = r"C:\Users\imade\OneDrive\מסמכים\projet python\data_GI\smart_logistics.csv"

# Chargement du fichier
df = pd.read_csv(path, encoding="utf-8", sep=",")

# Conversion des colonnes nécessaires
df["Timestamp"] = pd.to_datetime(df["Timestamp"], errors="coerce")
df["Inventory_Level"] = pd.to_numeric(df["Inventory_Level"], errors="coerce")
df["Demand_Forecast"] = pd.to_numeric(df["Demand_Forecast"], errors="coerce")
df["Waiting_Time"] = pd.to_numeric(df["Waiting_Time"], errors="coerce")

# Filtrer uniquement Truck_6
truck6 = df[df["Asset_ID"] == "Truck_6"].sort_values("Timestamp")

# Tracer les trois séries
plt.figure(figsize=(12,6))
plt.plot(truck6["Timestamp"], truck6["Inventory_Level"], label="Inventory_Level", marker="o")
plt.plot(truck6["Timestamp"], truck6["Demand_Forecast"], label="Demand_Forecast", marker="s")
plt.plot(truck6["Timestamp"], truck6["Waiting_Time"], label="Waiting_Time", marker="^")

plt.title("Évolution pour Truck_6 : Stock vs Prévision vs Temps d'attente")
plt.xlabel("Timestamp")
plt.ylabel("Valeurs")
plt.legend()
plt.grid(True)
plt.tight_layout()
plt.show()
