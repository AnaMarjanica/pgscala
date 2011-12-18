package hr.element.pgscala.util;

import java.util.*;
import java.io.*;

public class PGIdent {
  // -----------------------------------------------------------------------------

  /**
   * All keywords except unreserved ones in lowercase.
   *
   * See also list in PostgreSQL source file: /src/include/parser/kwlist.h
   */

  protected static Set<String> keywords = new LinkedHashSet<String>();
  static {
    try {
      final BufferedReader bR = new BufferedReader(new InputStreamReader(
          PGIdent.class.getResourceAsStream("/keywords.reserved"), "UTF-8"));

      while (true) {
        final String line = bR.readLine();
        if (null == line) {
          break;
        }
        keywords.add(line);
      }

      bR.close();
    } catch (final Throwable t) {
      throw new RuntimeException("Could not load reserved keywords!", t);
    }
  }

  /** Volatile cache for quoted identities. */

  private static Map<String, String> identCache = Collections
      .synchronizedMap(new WeakHashMap<String, String>());

  /**
   * Replaces all quotes with double quotes, and surrounds in quotes; mimicking
   * PostgreSQL quote_ident(text) function.
   *
   * Can avoid quoting if ident starts with a lowercase letter or underscore and
   * contains only lowercase letters, digits, and underscores, *and* is not any
   * SQL keyword. Otherwise, supply quotes:
   *
   * SELECT * FROM public."analyse"; (Since analyse is a reserved keyword;
   * British spelling of analyze.)
   *
   * See also quote_identifier(const char *ident) in PostgreSQL source file:
   * /src/backend/utils/adt/ruleutils.c
   */

  public static String quote(final String ident) {
    if (null == ident) {
      throw new IllegalArgumentException("Ident cannot be null!");
    }

    {
      final String cacheHit = identCache.get(ident);

      if (null != cacheHit) {
        return cacheHit;
      }
    }

    final int len = ident.length();
    boolean quoting = len == 0;

    if (!quoting) {
      final char ch0 = ident.charAt(0);

      if (!(ch0 >= 'a' && ch0 <= 'z' || ch0 == '_')) {
        quoting = true;
      }
    }

    final int total;
    {
      int cnt = 0;
      for (int i = 0; i < len; i++) {
        final char ch = ident.charAt(i);
        if (!(ch >= 'a' && ch <= 'z' || ch >= '0' && ch <= '9' || ch == '_')) {
          quoting = true;

          if (ch == '"') {
            cnt++;
          }
        }
      }
      total = cnt;
    }

    if (!quoting && (total == 0) && !keywords.contains(ident)) {
      identCache.put(ident, ident);
      return ident;
    }

    final int newLen = len + 2 + total;
    final char[] quoted = new char[newLen];
    {
      quoted[0] = quoted[newLen - 1] = '"';

      if (total == 0) {
        for (int i = 0; i < len; i++) {
          quoted[i + 1] = ident.charAt(i);
        }
      } else {
        int cnt = 1;
        for (int i = 0; i < len; i++) {
          final char ch = ident.charAt(i);
          quoted[cnt + i] = ch;

          if (ch == '"') {
            quoted[++cnt + i] = ch;
          }
        }
      }
    }

    final String quotedIdent = new String(quoted);
    identCache.put(ident, quotedIdent);
    return quotedIdent;
  }

  // -----------------------------------------------------------------------------
}