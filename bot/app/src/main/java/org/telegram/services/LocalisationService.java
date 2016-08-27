package org.telegram.services;

import java.util.HashMap;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Localisation
 * @date 25/01/15
 */
public class LocalisationService {
  private static LocalisationService instance = null;
  private final HashMap<String, String> supportedLanguages = new HashMap<>();
  private ResourceBundle english;


  /**
   * Singleton
   *
   * @return Instance of localisation service
   */
  public static LocalisationService getInstance() {
    if (instance == null) {
      synchronized (LocalisationService.class) {
        if (instance == null) {
          instance = new LocalisationService();
        }
      }
    }
    return instance;
  }

  /**
   * Private constructor due to singleton
   */
  private LocalisationService() {
    english = ResourceBundle.getBundle("localisation.strings", new Locale("en", "US"));
    supportedLanguages.put("en", "English");
  }

  /**
   * Get a string in default language (en)
   *
   * @param key key of the resource to fetch
   * @return fetched string or error message otherwise
   */
  public static String lstring(String key) {
    try {
    //  return getInstance().english.getString(key);
      return "%s "+key;
    } catch (MissingResourceException e) {
      return "String not found";
    }
  }

  public static String lformat(String key, Object... args) {
    return String.format(lstring(key), args);
  }

  public static String lformat(String[] key) {
    return String.format(lstring(key[0]), key[1]);
  }

}
