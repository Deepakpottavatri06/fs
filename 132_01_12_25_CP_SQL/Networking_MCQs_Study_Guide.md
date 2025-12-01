# Networking MCQs Study Guide

## Question 1
**A /30 subnet mask is typically used for:**

| Option | Answer |
|--------|--------|
| a. | Multicast networks |
| b. | DHCP pools |
| c. | LAN segments |
| **d.** | **WAN point-to-point links ✓** |

### Concept:
A /30 subnet provides only 4 IP addresses (2^2 = 4), with 2 usable host addresses (network and broadcast addresses are reserved). This is perfect for WAN point-to-point links where only 2 devices (routers) need to communicate.

### Why others are wrong:
- **a. Multicast networks**: Multicast uses Class D addresses (224.0.0.0 - 239.255.255.255), not related to /30 subnets
- **b. DHCP pools**: DHCP pools require many addresses for multiple clients, /30 is too small
- **c. LAN segments**: LANs typically need many host addresses for workstations, servers, etc.

---

## Question 2
**A network with subnet mask 255.255.255.192 belongs to which prefix?**

| Option | Answer |
|--------|--------|
| a. | /25 |
| b. | /28 |
| **c.** | **/26 ✓** |
| d. | /27 |

### Concept:
255.255.255.192 in binary is: 11111111.11111111.11111111.11000000
- Count the 1s: 24 + 2 = 26 bits
- 192 = 128 + 64 = 2 bits in the last octet

### Why others are wrong:
- **a. /25**: 255.255.255.128 (1 bit borrowed = 128)
- **b. /28**: 255.255.255.240 (4 bits borrowed = 240)
- **d. /27**: 255.255.255.224 (3 bits borrowed = 224)

---

## Question 3
**How many hosts are usable in a /29 network?**

| Option | Answer |
|--------|--------|
| a. | 4 |
| b. | 8 |
| c. | 2 |
| **d.** | **6 ✓** |

### Concept:
- /29 means 32 - 29 = 3 host bits
- Total addresses = 2^3 = 8
- Usable hosts = 8 - 2 = **6** (subtract network and broadcast addresses)

### Why others are wrong:
- **a. 4**: This would be for /30 (2^2 - 2 = 2, not 4)
- **b. 8**: This is total addresses, not usable hosts
- **c. 2**: This would be usable hosts for /30

---

## Question 4
**In the OSI model, which layer provides end-to-end process-to-process delivery?**

| Option | Answer |
|--------|--------|
| a. | Physical Layer |
| b. | Data Link Layer |
| **c.** | **Transport Layer ✓** |
| d. | Network Layer |

### Concept:
The Transport Layer (Layer 4) provides:
- End-to-end communication between processes using port numbers
- Segmentation and reassembly
- Flow control and error recovery (TCP)
- Process-to-process delivery via ports

### Why others are wrong:
- **a. Physical Layer**: Deals with bits and physical transmission
- **b. Data Link Layer**: Provides node-to-node (hop-to-hop) delivery using MAC addresses
- **d. Network Layer**: Provides host-to-host delivery using IP addresses, not process-to-process

---

## Question 5
**In the TCP/IP model, which layer includes HTTP, DNS, and FTP?**

| Option | Answer |
|--------|--------|
| a. | Transport Layer |
| **b.** | **Application Layer ✓** |
| c. | Link Layer |
| d. | Internet Layer |

### Concept:
The Application Layer in TCP/IP model combines OSI's Application, Presentation, and Session layers. It includes protocols that directly interact with applications:
- HTTP (Web browsing)
- DNS (Domain name resolution)
- FTP (File transfer)
- SMTP, POP3, IMAP (Email)

### Why others are wrong:
- **a. Transport Layer**: Contains TCP and UDP protocols
- **c. Link Layer**: Handles physical addressing and frame transmission
- **d. Internet Layer**: Handles IP addressing and routing

---

## Question 6
**The maximum size of an Ethernet MAC address is:**

| Option | Answer |
|--------|--------|
| a. | 32 bits |
| b. | 64 bits |
| c. | 128 bits |
| **d.** | **48 bits ✓** |

### Concept:
Ethernet MAC (Media Access Control) addresses are:
- 48 bits (6 bytes) long
- Written as 6 pairs of hexadecimal digits (e.g., AA:BB:CC:DD:EE:FF)
- First 24 bits = OUI (Organizationally Unique Identifier)
- Last 24 bits = Device identifier

### Why others are wrong:
- **a. 32 bits**: This is the size of an IPv4 address
- **b. 64 bits**: Not used for MAC addresses
- **c. 128 bits**: This is the size of an IPv6 address

---

## Question 7
**What is the total number of subnets created when borrowing 3 bits from the host portion?**

| Option | Answer |
|--------|--------|
| **a.** | **8 ✓** |
| b. | 4 |
| c. | 16 |
| d. | 6 |

### Concept:
Number of subnets = 2^n where n = borrowed bits
- 2^3 = **8 subnets**

### Why others are wrong:
- **b. 4**: Would be 2^2 (2 bits borrowed)
- **c. 16**: Would be 2^4 (4 bits borrowed)
- **d. 6**: Not a power of 2, invalid for subnetting

---

## Question 8
**What type of NAT allows multiple private IPs to share a single public IP?**

| Option | Answer |
|--------|--------|
| a. | Static NAT |
| b. | One-to-One NAT |
| c. | Dynamic NAT |
| **d.** | **PAT (Port Address Translation) ✓** |

### Concept:
PAT (also called NAT Overload or NAPT):
- Maps multiple private IPs to a single public IP
- Uses different source port numbers to distinguish connections
- Most common NAT type for home/office networks

### Why others are wrong:
- **a. Static NAT**: One-to-one permanent mapping between private and public IP
- **b. One-to-One NAT**: Same as Static NAT
- **c. Dynamic NAT**: Maps private IPs to a pool of public IPs (still one-to-one, but dynamic)

---

## Question 9
**Which congestion control mechanism triggers retransmission before timeout?**

| Option | Answer |
|--------|--------|
| **a.** | **Fast Retransmit ✓** |
| b. | Slow Start |
| c. | Congestion Avoidance |
| d. | Fast Recovery |

### Concept:
Fast Retransmit:
- Triggered when sender receives 3 duplicate ACKs
- Retransmits lost segment immediately without waiting for timeout
- Much faster recovery than waiting for RTO (Retransmission Timeout)

### Why others are wrong:
- **b. Slow Start**: Initial phase where congestion window grows exponentially
- **c. Congestion Avoidance**: Phase where congestion window grows linearly
- **d. Fast Recovery**: Works WITH Fast Retransmit, but handles what happens AFTER retransmission (reduces cwnd by half instead of going to slow start)

---

## Question 10
**Which error correction technique can correct single-bit errors?**

| Option | Answer |
|--------|--------|
| a. | LRC |
| b. | CRC |
| c. | VRC |
| **d.** | **Hamming Code ✓** |

### Concept:
Hamming Code:
- Forward Error Correction (FEC) technique
- Adds redundant parity bits at positions that are powers of 2
- Can detect and CORRECT single-bit errors
- Can detect (but not correct) double-bit errors

### Why others are wrong:
- **a. LRC (Longitudinal Redundancy Check)**: Detection only, cannot correct
- **b. CRC (Cyclic Redundancy Check)**: Detection only, very powerful but cannot correct
- **c. VRC (Vertical Redundancy Check/Parity)**: Detection only, simple parity check

---

## Question 11
**Which error detection method can detect all single-bit errors and most multi-bit errors?**

| Option | Answer |
|--------|--------|
| **a.** | **CRC ✓** |
| b. | LRC |
| c. | Parity |
| d. | Checksum |

### Concept:
CRC (Cyclic Redundancy Check):
- Uses polynomial division for error detection
- Detects all single-bit errors
- Detects all double-bit errors
- Detects all odd number of bit errors
- Detects burst errors up to CRC length
- Industry standard for Ethernet, storage devices

### Why others are wrong:
- **b. LRC**: Can miss some error patterns
- **c. Parity**: Only detects odd number of bit errors, misses even number of errors
- **d. Checksum**: Weaker than CRC, can miss some multi-bit errors

---

## Question 12
**Which error detection technique uses polynomial division on data bits?**

| Option | Answer |
|--------|--------|
| a. | VRC |
| b. | Checksum |
| c. | LRC |
| **d.** | **CRC ✓** |

### Concept:
CRC (Cyclic Redundancy Check):
- Treats data as a large binary polynomial
- Divides by a generator polynomial using modulo-2 division
- Remainder becomes the CRC value appended to data
- Common polynomials: CRC-32 (Ethernet), CRC-16

### Why others are wrong:
- **a. VRC**: Uses simple parity bit calculation
- **b. Checksum**: Uses arithmetic sum of data segments
- **c. LRC**: Uses XOR operation on data blocks

---

## Question 13
**Which ICMP message is used by the ping command?**

| Option | Answer |
|--------|--------|
| a. | Time Exceeded |
| b. | Redirect |
| **c.** | **Echo Request / Echo Reply ✓** |
| d. | Destination Unreachable |

### Concept:
Ping uses ICMP:
- **Echo Request (Type 8)**: Sent by source
- **Echo Reply (Type 0)**: Returned by destination
- Tests reachability and round-trip time

### Why others are wrong:
- **a. Time Exceeded**: Used by traceroute when TTL expires
- **b. Redirect**: Router informs host of better route
- **d. Destination Unreachable**: Reports when destination cannot be reached

---

## Question 14
**Which interior routing protocol uses Dijkstra's SPF algorithm?**

| Option | Answer |
|--------|--------|
| a. | BGP |
| b. | EIGRP |
| **c.** | **OSPF ✓** |
| d. | RIP |

### Concept:
OSPF (Open Shortest Path First):
- Link-state routing protocol
- Uses Dijkstra's Shortest Path First (SPF) algorithm
- Builds complete topology map of the network
- Calculates shortest path to all destinations

### Why others are wrong:
- **a. BGP**: Path-vector protocol, uses AS-PATH, not Dijkstra
- **b. EIGRP**: Uses DUAL (Diffusing Update Algorithm)
- **d. RIP**: Uses Bellman-Ford algorithm (distance-vector)

---

## Question 15
**Which IPv4 header field is decremented at every router hop?**

| Option | Answer |
|--------|--------|
| a. | Fragment Offset |
| b. | Identification |
| c. | Flags |
| **d.** | **TTL (Time-To-Live) ✓** |

### Concept:
TTL (Time-To-Live):
- Starts with initial value (typically 64, 128, or 255)
- Decremented by 1 at each router hop
- When TTL reaches 0, packet is discarded
- Prevents infinite routing loops
- ICMP Time Exceeded sent back to source

### Why others are wrong:
- **a. Fragment Offset**: Used for packet fragmentation, not modified per hop
- **b. Identification**: Unique ID for fragmentation reassembly
- **c. Flags**: Control fragmentation (DF, MF bits)

---

## Question 16
**Which IPv6 feature removes the need for NAT?**

| Option | Answer |
|--------|--------|
| a. | Link-local addressing |
| b. | Flow Label Field |
| c. | Stateless Autoconfiguration |
| **d.** | **Unique Global Addresses ✓** |

### Concept:
IPv6 Unique Global Addresses:
- 128-bit address space provides virtually unlimited addresses
- Every device can have a globally unique, routable IP address
- NAT was created due to IPv4 address shortage
- With abundant IPv6 addresses, NAT is unnecessary

### Why others are wrong:
- **a. Link-local addressing**: For local communication only (fe80::), not routable
- **b. Flow Label Field**: For QoS and flow identification
- **c. Stateless Autoconfiguration (SLAAC)**: Auto-configuration method, doesn't eliminate NAT need

---

## Question 17
**Which of the following is a valid Class C IP address?**

| Option | Answer |
|--------|--------|
| a. | 172.16.10.20 |
| **b.** | **192.168.12.1 ✓** |
| c. | 10.23.4.5 |
| d. | 224.1.2.3 |

### Concept:
IP Address Classes:
- **Class A**: 1.0.0.0 - 126.255.255.255 (first octet 1-126)
- **Class B**: 128.0.0.0 - 191.255.255.255 (first octet 128-191)
- **Class C**: 192.0.0.0 - 223.255.255.255 (first octet 192-223)
- **Class D**: 224.0.0.0 - 239.255.255.255 (Multicast)

### Why others are wrong:
- **a. 172.16.10.20**: Class B address (172 is between 128-191)
- **c. 10.23.4.5**: Class A address (10 is between 1-126)
- **d. 224.1.2.3**: Class D (Multicast) address

---

## Question 18
**Which of the following uses AS-PATH as its primary metric?**

| Option | Answer |
|--------|--------|
| a. | EIGRP |
| b. | RIP |
| c. | OSPF |
| **d.** | **BGP ✓** |

### Concept:
BGP (Border Gateway Protocol):
- Path-vector protocol for inter-domain routing
- AS-PATH: List of Autonomous Systems a route traverses
- Shorter AS-PATH generally preferred
- Other BGP attributes: Local Preference, MED, Origin

### Why others are wrong:
- **a. EIGRP**: Uses composite metric (bandwidth, delay, load, reliability)
- **b. RIP**: Uses hop count as metric
- **c. OSPF**: Uses cost (based on bandwidth) as metric

---

## Question 19
**Which OSI layer adds physical addresses and performs framing?**

| Option | Answer |
|--------|--------|
| a. | Transport Layer |
| b. | Network Layer |
| c. | Presentation Layer |
| **d.** | **Data Link Layer ✓** |

### Concept:
Data Link Layer (Layer 2):
- Adds MAC addresses (physical addressing)
- Creates frames from packets
- Error detection (CRC)
- Media access control (CSMA/CD, CSMA/CA)
- Switches operate at this layer

### Why others are wrong:
- **a. Transport Layer**: Adds port numbers, creates segments
- **b. Network Layer**: Adds logical (IP) addresses, creates packets
- **c. Presentation Layer**: Handles encryption, compression, format conversion

---

## Question 20
**Which OSI layer breaks data into segments?**

| Option | Answer |
|--------|--------|
| a. | Data Link Layer |
| b. | Application Layer |
| **c.** | **Transport Layer ✓** |
| d. | Network Layer |

### Concept:
Transport Layer (Layer 4):
- Breaks data into **segments** (TCP) or **datagrams** (UDP)
- Adds port numbers for process identification
- Provides flow control and error recovery
- PDU (Protocol Data Unit) = Segment

### Why others are wrong:
- **a. Data Link Layer**: Creates frames from packets
- **b. Application Layer**: Generates data, no segmentation
- **d. Network Layer**: Creates packets from segments

---

## Question 21
**Which OSI layer is responsible for encryption and data representation?**

| Option | Answer |
|--------|--------|
| a. | Transport Layer |
| b. | Application Layer |
| **c.** | **Presentation Layer ✓** |
| d. | Session Layer |

### Concept:
Presentation Layer (Layer 6):
- **Encryption/Decryption**: SSL/TLS operations
- **Data format translation**: ASCII, EBCDIC, Unicode
- **Compression/Decompression**
- **Data representation**: Ensures data is readable by receiving system

### Why others are wrong:
- **a. Transport Layer**: Reliability, flow control, segmentation
- **b. Application Layer**: User interface, application services
- **d. Session Layer**: Dialog control, session management

---

## Question 22
**Which OSI layer is responsible for synchronizing dialog control and managing checkpoints during communication sessions?**

| Option | Answer |
|--------|--------|
| **a.** | **Session Layer ✓** |
| b. | Presentation Layer |
| c. | Data Link Layer |
| d. | Transport Layer |

### Concept:
Session Layer (Layer 5):
- **Dialog control**: Half-duplex or full-duplex communication
- **Synchronization**: Checkpoints for recovery
- **Session management**: Establish, maintain, terminate sessions
- **Token management**: Controls who can transmit

### Why others are wrong:
- **b. Presentation Layer**: Data format and encryption
- **c. Data Link Layer**: Framing and MAC addressing
- **d. Transport Layer**: End-to-end reliability

---

## Question 23
**Which part of the IPv4 header ensures integrity of the header fields?**

| Option | Answer |
|--------|--------|
| a. | Identification Field |
| b. | Version Field |
| **c.** | **Header Checksum ✓** |
| d. | Options Field |

### Concept:
Header Checksum:
- 16-bit field in IPv4 header
- Calculated over header fields only (not data)
- Recalculated at each hop (because TTL changes)
- Detects corruption in header during transmission
- Note: IPv6 removed this field for efficiency

### Why others are wrong:
- **a. Identification Field**: Used for fragment reassembly
- **b. Version Field**: Indicates IP version (4 or 6)
- **d. Options Field**: Optional features like source routing

---

## Question 24
**Which process converts domain names to IP addresses?**

| Option | Answer |
|--------|--------|
| a. | NAT |
| **b.** | **DNS ✓** |
| c. | DHCP |
| d. | ARP |

### Concept:
DNS (Domain Name System):
- Translates human-readable domain names (www.google.com) to IP addresses
- Hierarchical distributed database
- Uses UDP port 53 (TCP for zone transfers)
- Query types: A (IPv4), AAAA (IPv6), MX (mail), etc.

### Why others are wrong:
- **a. NAT**: Translates private IPs to public IPs
- **c. DHCP**: Assigns IP configuration to hosts automatically
- **d. ARP**: Resolves IP addresses to MAC addresses

---

## Question 25
**Which protocol automatically assigns IP, subnet mask, and default gateway to hosts?**

| Option | Answer |
|--------|--------|
| a. | ARP |
| **b.** | **DHCP ✓** |
| c. | ICMP |
| d. | SNMP |

### Concept:
DHCP (Dynamic Host Configuration Protocol):
- Automatically assigns: IP address, Subnet mask, Default gateway, DNS servers
- Uses UDP ports 67 (server) and 68 (client)
- DORA process: Discover, Offer, Request, Acknowledge
- Reduces manual configuration errors

### Why others are wrong:
- **a. ARP**: Maps IP to MAC addresses
- **c. ICMP**: Error reporting and diagnostics
- **d. SNMP**: Network management and monitoring

---

## Question 26
**Which protocol is used for error reporting and network diagnostics?**

| Option | Answer |
|--------|--------|
| a. | ARP |
| b. | DHCP |
| c. | SMTP |
| **d.** | **ICMP ✓** |

### Concept:
ICMP (Internet Control Message Protocol):
- Reports network errors and conditions
- Diagnostic tools: ping (Echo Request/Reply), traceroute (Time Exceeded)
- Messages: Destination Unreachable, Redirect, Source Quench
- Works at Network Layer alongside IP

### Why others are wrong:
- **a. ARP**: Address resolution (IP to MAC)
- **b. DHCP**: Dynamic IP configuration
- **c. SMTP**: Simple Mail Transfer Protocol for email

---

## Question 27
**Which protocol maps an IPv4 address to a MAC address on a LAN?**

| Option | Answer |
|--------|--------|
| a. | RARP |
| b. | DNS |
| **c.** | **ARP ✓** |
| d. | ICMP |

### Concept:
ARP (Address Resolution Protocol):
- Resolves IPv4 address → MAC address
- Broadcasts ARP Request on local network
- Target responds with ARP Reply containing MAC address
- ARP cache stores mappings for efficiency

### Why others are wrong:
- **a. RARP**: Reverse ARP - maps MAC to IP (obsolete, replaced by DHCP)
- **b. DNS**: Maps domain names to IP addresses
- **d. ICMP**: Error reporting, not address resolution

---

## Question 28
**Which protocol traces the route taken by packets across routers?**

| Option | Answer |
|--------|--------|
| a. | DNS |
| b. | DHCP |
| c. | ARP |
| **d.** | **ICMP Time Exceeded ✓** |

### Concept:
Traceroute uses ICMP Time Exceeded:
- Sends packets with incrementing TTL (1, 2, 3...)
- Each router decrements TTL; when TTL=0, sends ICMP Time Exceeded
- This reveals each hop along the path
- Windows uses ICMP Echo; Unix uses UDP

### Why others are wrong:
- **a. DNS**: Domain name resolution
- **b. DHCP**: IP configuration
- **c. ARP**: Local MAC address resolution

---

## Question 29
**Which routing protocol is used for inter-domain (between AS) routing?**

| Option | Answer |
|--------|--------|
| a. | RIP |
| b. | EIGRP |
| **c.** | **BGP ✓** |
| d. | OSPF |

### Concept:
BGP (Border Gateway Protocol):
- Exterior Gateway Protocol (EGP)
- Routes between Autonomous Systems (AS)
- Used on the Internet backbone
- Path-vector protocol using AS-PATH
- eBGP (external) and iBGP (internal)

### Why others are wrong:
- **a. RIP**: Interior Gateway Protocol (IGP)
- **b. EIGRP**: Cisco proprietary IGP
- **d. OSPF**: IGP using link-state

---

## Question 30
**Which routing protocol uses both distance-vector and link-state features (hybrid)?**

| Option | Answer |
|--------|--------|
| a. | OSPF |
| b. | RIP |
| c. | BGP |
| **d.** | **EIGRP ✓** |

### Concept:
EIGRP (Enhanced Interior Gateway Routing Protocol):
- Called "Advanced Distance Vector" or "Hybrid"
- Distance-vector: Exchanges routing information with neighbors
- Link-state features: Maintains neighbor relationships, fast convergence
- Uses DUAL algorithm
- Cisco proprietary (now open standard)

### Why others are wrong:
- **a. OSPF**: Pure link-state protocol
- **b. RIP**: Pure distance-vector protocol
- **c. BGP**: Path-vector protocol

---

## Question 31
**Which routing protocol uses Distance Vector and a maximum hop count limitation?**

| Option | Answer |
|--------|--------|
| a. | BGP |
| **b.** | **RIP ✓** |
| c. | IS-IS |
| d. | OSPF |

### Concept:
RIP (Routing Information Protocol):
- Distance-vector protocol
- Maximum hop count: 15 (16 = unreachable)
- Uses Bellman-Ford algorithm
- Updates every 30 seconds
- Simple but limited scalability

### Why others are wrong:
- **a. BGP**: Path-vector, no hop count limit
- **c. IS-IS**: Link-state protocol
- **d. OSPF**: Link-state protocol, no hop count limit

---

## Question 32
**Which subnet mask corresponds to exactly 512 hosts?**

| Option | Answer |
|--------|--------|
| a. | /22 |
| b. | /24 |
| **c.** | **/23 ✓** |
| d. | /25 |

### Concept:
To calculate usable hosts: 2^n - 2 (where n = host bits)
- /23 = 32 - 23 = 9 host bits
- 2^9 = 512 total addresses
- 512 - 2 = 510 usable hosts

Wait, let's verify: For exactly 512 hosts (usable), we need 2^n - 2 = 512, so 2^n = 514 (not a power of 2)

Actually, /23 gives 510 usable hosts, which is closest to 512. The question likely means approximately 512 or total addresses.

### Why others are wrong:
- **a. /22**: 10 host bits = 1022 usable hosts
- **b. /24**: 8 host bits = 254 usable hosts  
- **d. /25**: 7 host bits = 126 usable hosts

---

## Question 33
**Which TCP mechanism ensures reliable connection establishment before data transfer begins?**

| Option | Answer |
|--------|--------|
| a. | Fast Retransmit |
| b. | Slow Start |
| **c.** | **Three-Way Handshake ✓** |
| d. | Sliding Window |

### Concept:
Three-Way Handshake:
1. **SYN**: Client sends SYN to server
2. **SYN-ACK**: Server responds with SYN-ACK
3. **ACK**: Client sends ACK, connection established

Ensures both sides are ready and agree on initial sequence numbers.

### Why others are wrong:
- **a. Fast Retransmit**: Recovery mechanism after connection is established
- **b. Slow Start**: Congestion control after connection
- **d. Sliding Window**: Flow control during data transfer

---

## Question 34
**Which TCP mechanism is used to prevent overwhelming the receiver?**

| Option | Answer |
|--------|--------|
| a. | Congestion Window |
| **b.** | **Flow Control (Sliding Window) ✓** |
| c. | Maximum Segment Size |
| d. | Checksum |

### Concept:
Flow Control using Sliding Window:
- Receiver advertises available buffer space (Window Size)
- Sender limits data to receiver's capacity
- Prevents receiver buffer overflow
- Dynamic adjustment based on receiver feedback

### Why others are wrong:
- **a. Congestion Window**: Prevents overwhelming the **network**, not receiver
- **c. Maximum Segment Size**: Determines segment size, not flow control
- **d. Checksum**: Error detection, not flow control

---

## Question 35
**Which TCP/IP layer is responsible for logical addressing and routing decisions?**

| Option | Answer |
|--------|--------|
| **a.** | **Network (Internet) Layer ✓** |
| b. | Link Layer |
| c. | Transport Layer |
| d. | Application Layer |

### Concept:
Network (Internet) Layer:
- Logical addressing (IP addresses)
- Routing decisions (path selection)
- Packet forwarding between networks
- Protocols: IP, ICMP, ARP, routing protocols

### Why others are wrong:
- **b. Link Layer**: Physical addressing (MAC), local delivery
- **c. Transport Layer**: Port addressing, end-to-end delivery
- **d. Application Layer**: Application protocols (HTTP, FTP, DNS)

---

## Question 36
**Which technique is used by CRC to detect burst errors?**

| Option | Answer |
|--------|--------|
| a. | Exponential Backoff |
| b. | Parity Check |
| c. | Hamming Distance |
| **d.** | **Polynomial Modulo-2 Division ✓** |

### Concept:
CRC uses Polynomial Modulo-2 Division:
- Data treated as binary polynomial
- Divided by generator polynomial
- Uses XOR operations (modulo-2)
- Remainder = CRC value
- Detects burst errors up to polynomial degree

### Why others are wrong:
- **a. Exponential Backoff**: Collision handling in CSMA/CD
- **b. Parity Check**: Simple single-bit error detection
- **c. Hamming Distance**: Measures difference between codes, used in Hamming codes

---

## Question 37
**Which transport layer protocol is connectionless and best suited for real-time applications?**

| Option | Answer |
|--------|--------|
| a. | ICMP |
| b. | SCTP |
| **c.** | **UDP ✓** |
| d. | TCP |

### Concept:
UDP (User Datagram Protocol):
- Connectionless (no handshake)
- No reliability, ordering, or flow control
- Low overhead, low latency
- Best for: VoIP, video streaming, gaming, DNS queries

### Why others are wrong:
- **a. ICMP**: Network layer protocol, not transport
- **b. SCTP**: Connection-oriented, reliable (like TCP with multi-homing)
- **d. TCP**: Connection-oriented, reliable, higher latency

---

## Question 38
**Which type of switching is used in modern IP networks?**

| Option | Answer |
|--------|--------|
| a. | Circuit Switching |
| **b.** | **Packet Switching ✓** |
| c. | Virtual Channel Switching |
| d. | Message Switching |

### Concept:
Packet Switching:
- Data broken into packets
- Each packet routed independently
- Efficient use of bandwidth (statistical multiplexing)
- Used in IP/Internet networks
- Two types: Datagram (IP) and Virtual Circuit (ATM)

### Why others are wrong:
- **a. Circuit Switching**: Dedicated path (traditional telephone networks)
- **c. Virtual Channel Switching**: Type of packet switching used in ATM, not modern IP
- **d. Message Switching**: Store-and-forward entire messages (obsolete)

---

## Summary Table: Questions You Got Wrong

| Question | Topic | Correct Answer |
|----------|-------|----------------|
| Q6 | MAC Address Size | d. 48 bits |
| Q8 | NAT Types | d. PAT (Port Address Translation) |
| Q9 | Congestion Control | a. Fast Retransmit |
| Q11 | Error Detection | a. CRC |
| Q18 | Routing Protocol Metrics | d. BGP (uses AS-PATH) |
| Q30 | Hybrid Routing Protocol | d. EIGRP |
| Q31 | Distance Vector Protocol | b. RIP |
| Q38 | Network Switching | b. Packet Switching |

---

## Key Concepts Quick Reference

### Subnetting
| Prefix | Subnet Mask | Usable Hosts |
|--------|-------------|--------------|
| /24 | 255.255.255.0 | 254 |
| /25 | 255.255.255.128 | 126 |
| /26 | 255.255.255.192 | 62 |
| /27 | 255.255.255.224 | 30 |
| /28 | 255.255.255.240 | 14 |
| /29 | 255.255.255.248 | 6 |
| /30 | 255.255.255.252 | 2 |

### OSI Model Layers
| Layer | Name | Function | PDU |
|-------|------|----------|-----|
| 7 | Application | User interface | Data |
| 6 | Presentation | Encryption, format | Data |
| 5 | Session | Dialog control | Data |
| 4 | Transport | End-to-end delivery | Segment |
| 3 | Network | Routing, logical addressing | Packet |
| 2 | Data Link | Framing, MAC addressing | Frame |
| 1 | Physical | Bit transmission | Bits |

### Routing Protocols
| Protocol | Type | Algorithm | Metric |
|----------|------|-----------|--------|
| RIP | Distance Vector | Bellman-Ford | Hop Count (max 15) |
| OSPF | Link State | Dijkstra SPF | Cost (bandwidth) |
| EIGRP | Hybrid | DUAL | Composite |
| BGP | Path Vector | Best Path | AS-PATH |
