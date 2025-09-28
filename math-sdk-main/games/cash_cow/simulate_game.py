import csv

LOOKUP_FILE = "lookUpTable_base.csv"
TOTAL_ROUNDS = 1_000_000  # número de rodadas simuladas

def load_lookup():
    table = []
    with open(LOOKUP_FILE, "r", encoding="utf-8") as f:
        reader = csv.reader(f)
        for row in reader:
            if len(row) >= 3:
                sim_id = int(row[0])
                weight = int(row[1])
                payout = int(row[2])
                table.append((sim_id, weight, payout))
    return table

def simulate():
    lookup = load_lookup()

    total_weight = sum(w for _, w, _ in lookup)
    wins = 0
    total_return = 0
    max_win = 0
    dist = {}

    for sim_id, weight, payout in lookup:
        prob = weight / total_weight
        expected_hits = int(prob * TOTAL_ROUNDS)

        if payout > 0:
            wins += expected_hits

        # payout/100 porque está em centésimos
        total_return += (payout / 100) * expected_hits
        if payout > max_win:
            max_win = payout

        dist[payout] = dist.get(payout, 0) + expected_hits

    rtp = (total_return / TOTAL_ROUNDS) * 100
    hit_rate = (wins / TOTAL_ROUNDS) * 100

    print("📊 Simulation Results")
    print(f"Rounds simulated: {TOTAL_ROUNDS}")
    print(f"RTP: {rtp:.2f}%")
    print(f"Hit Rate (non-zero): {hit_rate:.2f}%")
    print(f"Max Win: {max_win/100:.0f}x")
    print(f"Zero Win Probability: {100-hit_rate:.2f}%\n")

    print("📌 Payout Distribution (expected hits):")
    for payout, hits in sorted(dist.items()):
        print(f"  {payout/100:.0f}x → {hits}")

if __name__ == "__main__":
    simulate()
