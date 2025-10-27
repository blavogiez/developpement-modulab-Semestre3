import os
import pandas as pd
import matplotlib.pyplot as plt
from PIL import Image
from io import BytesIO

d = "res/benchmarks"
files = [f for f in os.listdir(d) if f.endswith(".csv")]
for i,f in enumerate(files): print(i,f)
i = int(input("Choix: "))

df = pd.read_csv(os.path.join(d, files[i]))

plt.plot(df["taille"], df["temps(ms)"])
plt.xlabel("taille")
plt.ylabel("temps (ms)")
plt.title(files[i])
plt.tight_layout()

buf = BytesIO()
plt.savefig(buf, format="png")
plt.show()
plt.close()

buf.seek(0)
img = Image.open(buf)
os.makedirs(d+"/modelisations", exist_ok=True)
img.save(os.path.join(d+"/modelisations", files[i].replace(".csv", ".png")))
