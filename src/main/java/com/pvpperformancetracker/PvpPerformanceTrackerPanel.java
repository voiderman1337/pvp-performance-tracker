/*
 * Copyright (c)  2020, Matsyir <https://github.com/Matsyir>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.pvpperformancetracker;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Inject;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.PluginPanel;

class PvpPerformanceTrackerPanel extends PluginPanel
{
	// The main fight history container, this will hold all the individual FightPerformancePanels.
	private final JPanel fightHistoryContainer = new JPanel();
	private final GridBagConstraints fightPanelConstraints = new GridBagConstraints();

	private final TotalStatsPanel totalStatsPanel = new TotalStatsPanel();
	private final JPopupMenu popupMenu = new JPopupMenu();

	@Inject
	private PvpPerformanceTrackerPanel(ClientThread clientThread, ItemManager itemManager, ScheduledExecutorService executor)
	{
		super(false);

		setLayout(new BorderLayout(0, 4));
		setBackground(ColorScheme.DARK_GRAY_COLOR);
		setBorder(new EmptyBorder(8, 8, 8, 8));
		JPanel mainContent = new JPanel(new BorderLayout());

		fightHistoryContainer.setSize(getSize());
		fightHistoryContainer.setLayout(new GridBagLayout());

		// constraints to initialize FightPerformancePanels with
		fightPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
		fightPanelConstraints.weightx = 1;
		fightPanelConstraints.gridx = 0;
		fightPanelConstraints.gridy = 0;
		fightPanelConstraints.insets = new Insets(0, 0, 4, 0);

		add(totalStatsPanel, BorderLayout.NORTH, 0);

		// wrap mainContent with scrollpane so it has a scrollbar
		JScrollPane scrollableContainer = new JScrollPane(mainContent);
		scrollableContainer.setBackground(ColorScheme.DARK_GRAY_COLOR);
		scrollableContainer.getVerticalScrollBar().setPreferredSize(new Dimension(6, 0));

		// initialize context menu
		popupMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
		// Create "Reset All" popup menu item
		final JMenuItem reset = new JMenuItem("Reset All");
		reset.addActionListener(e ->
		{
			totalStatsPanel.reset();
			fightHistoryContainer.removeAll();
			fightPanelConstraints.gridy = 0;
			SwingUtilities.invokeLater(this::updateUI);
		});
		popupMenu.add(reset);

		setComponentPopupMenu(popupMenu);
		mainContent.setComponentPopupMenu(popupMenu);

		mainContent.add(fightHistoryContainer, BorderLayout.NORTH);
		add(scrollableContainer, BorderLayout.CENTER);
	}

	public void addFight(FightPerformance fight)
	{
		SwingUtilities.invokeLater(() ->
		{
			totalStatsPanel.addFight(fight);

			FightPerformancePanel panel = new FightPerformancePanel(fight);
			panel.setComponentPopupMenu(popupMenu);
			fightHistoryContainer.add(panel, fightPanelConstraints);
			fightPanelConstraints.gridy++;

			updateUI();
		});
	}
}