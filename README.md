# sigsearch

Display summarised "signatures" for source code files.

## Summary options

Pass the set of characters `{};` through unmodified:

    c/{};/

Translate the set of characters `asft` to `ASFT` (unimplemented):

    tr/asft/ASFT/

Translate the symbol `new` into the character `n`:

    i/new/n/

Translate all symbols matching `Symbol$` to `s`:

    r/Symbol$/s/

Translate all strings matching `foo` to `f`:

    s/foo/f/

## Search options (unimplemented)

List all lines matching the regular expression `foo`:

    g/foo/

List all lines containing the symbol `foo`:

    l/foo/

## Input options

Exclude files whose names match `*.o`:

    -x'*.o'

Include files whose names match `*.java`

    -m'*.java'

## Example output

    >> sigsearch "-c{};" -inew=n src/main/java/me/dmorris/sigsearch/App.java
    src/main/java/me/dmorris/sigsearch/App.java: ;;;;{{n;n;n;n;n;n;{{n;}{;;n;}{;;n;}{;;n;}{;}}{;n;;{;};;{;};}}}

