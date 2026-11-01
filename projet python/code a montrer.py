

# 📦 Importation des bibliothèques nécessaires
import pandas as pd
import matplotlib.pyplot as plt

# 📁 Chemin vers le fichier CSV (à adapter selon ton emplacement)
path = r"C:\Users\imade\OneDrive\מסמכים\projet python\data_GI\smart_logistics.csv"

# 📥 Chargement du fichier CSV
df = pd.read_csv(path, encoding="utf-8", sep=",")

# 🧼 Conversion des colonnes en types numériques et datetime
df["Inventory_Level"] = pd.to_numeric(df["Inventory_Level"], errors="coerce")
df["Demand_Forecast"] = pd.to_numeric(df["Demand_Forecast"], errors="coerce")
df["Waiting_Time"] = pd.to_numeric(df["Waiting_Time"], errors="coerce")
df["Timestamp"] = pd.to_datetime(df["Timestamp"], errors="coerce")

# 🔍 Affichage de colonnes spécifiques
print("\n Inventory_Level et Demand_Forecast :")
print(df[["Inventory_Level", "Demand_Forecast"]].head(10))

print("\n Timestamp et Asset_ID :")
print(df[["Timestamp", "Asset_ID"]].head(10))

# 🔎 Filtrage : stock inférieur à la demande
df_understock = df[df["Inventory_Level"] < df["Demand_Forecast"]]
print("\n Lignes où Inventory_Level < Demand_Forecast :", len(df_understock))
print(df_understock.head(10))

# 🚦 Filtrage : statut de trafic = "Detour"
df_detour = df[df["Traffic_Status"] == "Detour"]
print("\n Lignes avec Traffic_Status = 'Detour' :", len(df_detour))
print(df_detour[["Timestamp", "Asset_ID"]].head(10))

# 🧮 Création de colonnes calculées
df["Stock_Safety_Level"] = df["Inventory_Level"] * 0.2
df["Demand_Gap"] = df["Demand_Forecast"] - df["Inventory_Level"]
print("\n Colonnes calculées :")
print(df[["Inventory_Level", "Demand_Forecast", "Stock_Safety_Level", "Demand_Gap"]].head(10))

# 📊 Agrégation : stock moyen par Asset_ID
stock_mean = df.groupby("Asset_ID")["Inventory_Level"].mean()
print("\n Stock moyen par Asset_ID :")
print(stock_mean)

# 📈 Agrégation : demande maximale par Shipment_Status
max_demand = df.groupby("Shipment_Status")["Demand_Forecast"].max()
print("\n Demande maximale par Shipment_Status :")
print(max_demand)

# 📉 Visualisation : évolution pour Truck_6
truck6 = df[df["Asset_ID"] == "Truck_6"].sort_values("Timestamp")

plt.figure(figsize=(12, 6))
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
