# alert-logic

A Clojure library designed to demonstrate a basic API implementation. Uses an atom as a fake database and has several
endpoints for add/deleting/listing and getting data, all can be used via CURL or through the swagger UI.

## Usage

1. Make sure you have https://leiningen.org/ and at least Java version 11 installed.
2. From the root of the project type `lein ring server`.
3. This should lunch a local webserver running on port 3000 (i.e. http://localhost:3000/)
4. Going to the above URL should display a swagger UI page with details about the various endpoints that can be used,
 just click "Try it out" to give it a go.

## License

Copyright © 2021 Russell Davey

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
