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

package com.djrapitops.extension;

import ch.dkrieger.coinsystem.bungeecord.event.ProxiedCoinPlayerCoinsChangeEvent;
import ch.dkrieger.coinsystem.bungeecord.event.ProxiedCoinPlayerColorSetEvent;
import ch.dkrieger.coinsystem.core.player.CoinPlayer;
import com.djrapitops.plan.extension.Caller;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.event.EventHandler;

public class DKCoinsBungeeDKCListener implements DKCListener, Listener {

    private final Caller caller;

    public DKCoinsBungeeDKCListener(Caller caller) {
        this.caller = caller;
    }

    @Override
    public void register() {
        PluginManager pluginManager = ProxyServer.getInstance().getPluginManager();
        Plugin plugin = pluginManager.getPlugin("Plan");
        pluginManager.registerListener(plugin, this);
    }

    @EventHandler
    public void onPlayerCoinsChange(ProxiedCoinPlayerCoinsChangeEvent event) {
        CoinPlayer coinPlayer = event.getCoinPlayer();
        caller.updatePlayerData(coinPlayer.getUUID(), coinPlayer.getName());
    }

    @EventHandler
    public void onPlayerColorSet(ProxiedCoinPlayerColorSetEvent event) {
        CoinPlayer coinPlayer = event.getPlayer();
        caller.updatePlayerData(coinPlayer.getUUID(), coinPlayer.getName());
    }

}
