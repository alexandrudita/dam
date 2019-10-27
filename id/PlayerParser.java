package ro.ase.eu.damapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PlayerParser {
    public static HttpResponse parseJson(String json) throws JSONException {

        HttpResponse response = new HttpResponse();

        JSONObject jsonObject = new JSONObject(json);
        response.setGoalkeeper(getItemArrayFromJson(jsonObject.getJSONArray(Constants.HTTP_GOALKEEPER_TAG)));
        response.setCenter(getItemArrayFromJson(jsonObject.getJSONArray(Constants.HTTP_CENTER_TAG)));
        response.setInter(getItemArrayFromJson(jsonObject.getJSONArray(Constants.HTTP_INTER_TAG)));
        response.setWinger(getItemArrayFromJson(jsonObject.getJSONArray(Constants.HTTP_WINGER_TAG)));

        return response;
    }

    private static List<Item> getItemArrayFromJson(JSONArray array) throws JSONException {

        if (array == null) {
            return null;
        }

        List<Item> result = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            Item item = getItemFromJson(array.getJSONObject(i));
            if (item != null) {
                result.add(item);
            }
        }
        return result;
    }

    private static Item getItemFromJson(JSONObject object) throws JSONException {

        if (object == null) {
            return null;
        }
        Item result = new Item();
        result.setTeam(object.getString(Constants.HTTP_TEAM_TAG));
        result.setExtraInfo(object.getString(Constants.HTTP_EXTRA_INFO_TAG));
        JSONObject playerInfoObject = object.getJSONObject(Constants.HTTP_PLAYER_INFO_TAG);
        if (playerInfoObject != null) {
            PlayerInfo playerInfo = new PlayerInfo();
            playerInfo.setName(playerInfoObject.getString(Constants.HTTP_NAME_TAG));
            playerInfo.setFavoriteHand(playerInfoObject.getString(Constants.HTTP_FAVORITE_HAND_TAG));
            try {
                Date birthday = new SimpleDateFormat(AddPlayerActivity.DATE_FORMAT, Locale.US)
                        .parse(playerInfoObject.getString(Constants.HTTP_BIRTHDAY_TAG));
                playerInfo.setBirthday(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            result.setPlayerInfo(playerInfo);
        }
        return result;
    }
}
