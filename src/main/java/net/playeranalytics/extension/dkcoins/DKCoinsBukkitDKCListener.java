/*
 * Copyright (c) 2019 Plan | Player Analytics
 *
 * The MIT License(MIT)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package net.playeranalytics.extension.dkcoins;

import ch.dkrieger.coinsystem.core.player.CoinPlayer;
import ch.dkrieger.coinsystem.spigot.event.BukkitCoinPlayerCoinsChangeEvent;
import ch.dkrieger.coinsystem.spigot.event.BukkitCoinPlayerColorSetEvent;
import com.djrapitops.plan.extension.Caller;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class DKCoinsBukkitDKCListener implements DKCListener, Listener {

    private final Caller caller;

    public DKCoinsBukkitDKCListener(Caller caller) {
        this.caller = caller;
    }

    @Override
    public void register() {
        Plugin plan = Bukkit.getPluginManager().getPlugin("Plan");
        Bukkit.getPluginManager().registerEvents(this, plan);
    }

    @EventHandler
    public void onPlayerCoinsChange(BukkitCoinPlayerCoinsChangeEvent event) {
        CoinPlayer coinPlayer = event.getCoinPlayer();
        caller.updatePlayerData(coinPlayer.getUUID(), coinPlayer.getName());
    }

    @EventHandler
    public void onPlayerColorSet(BukkitCoinPlayerColorSetEvent event) {
        CoinPlayer coinPlayer = event.getPlayer();
        caller.updatePlayerData(coinPlayer.getUUID(), coinPlayer.getName());
    }
}
