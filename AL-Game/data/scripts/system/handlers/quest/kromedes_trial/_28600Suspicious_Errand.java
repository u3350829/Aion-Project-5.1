/*
 * This file is part of Encom. **ENCOM FUCK OTHER SVN**
 *
 *  Encom is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Encom is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser Public License
 *  along with Encom.  If not, see <http://www.gnu.org/licenses/>.
 */
package quest.kromedes_trial;

import com.aionemu.gameserver.questEngine.handlers.QuestHandler;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;

/****/
/** Author Rinzler (Encom)
/****/

public class _28600Suspicious_Errand extends QuestHandler
{
	private final static int questId = 28600;
	
	public _28600Suspicious_Errand() {
		super(questId);
	}
	
	@Override
	public boolean onLvlUpEvent(QuestEnv env) {
		return defaultOnLvlUpEvent(env);
	}
	
	@Override
	public void register() {
		qe.registerOnLevelUp(questId);
		int[] npcs = {204702, 205233, 804607};
		for (int npc: npcs) {
			qe.registerQuestNpc(npc).addOnTalkEvent(questId);
			qe.registerQuestNpc(204702).addOnQuestStart(questId);
		}
	}
	
	@Override
	public boolean onDialogEvent(QuestEnv env) {
		if (sendQuestNoneDialog(env, 204702, 182213004, 1)) {
			return true;
		}
		QuestState qs = env.getPlayer().getQuestStateList().getQuestState(questId);
		if (qs == null) {
			return false;
		}
		int var = qs.getQuestVarById(0);
		if (qs.getStatus() == QuestStatus.START) {
			if (env.getTargetId() == 205233) {
				switch (env.getDialog()) {
					case START_DIALOG: {
						if (var == 0) {
							return sendQuestDialog(env, 1352);
						} else if (var == 2) {
							return sendQuestDialog(env, 2034);
						}
					} case STEP_TO_1: {
						return defaultCloseDialog(env, 0, 1);
					} case STEP_TO_3: {
						qs.setQuestVarById(0, 3);
						return defaultCloseDialog(env, 3, 3, true, false, 182213005, 1, 182213004, 1);
					}
				}
			} else if (env.getTargetId() == 804607) {
				switch (env.getDialog()) {
					case START_DIALOG: {
						if (var == 1) {
							return sendQuestDialog(env, 1693);
						}
					} case STEP_TO_2: {
						return defaultCloseDialog(env, 1, 2, false, false, 182213004, 1, 0, 0);
					}
				}
			}
		}
		return sendQuestRewardDialog(env, 204702, 2375);
	}
}