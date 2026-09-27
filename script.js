const LOT_CONFIG = {
  SMALL: 3,
  MEDIUM: 5,
  LARGE: 2,
};

const RATE_BY_TYPE = {
  SMALL: 10,
  MEDIUM: 20,
  LARGE: 50,
};

const TYPE_TO_SPOT = {
  BIKE: 'SMALL',
  CAR: 'MEDIUM',
  TRUCK: 'LARGE',
};

const state = {
  spots: [],
  tickets: [],
  nextTicketId: 1,
  lastReceipt: null,
};

function formatTime(date) {
  return new Intl.DateTimeFormat('en-GB', {
    dateStyle: 'medium',
    timeStyle: 'short',
  }).format(date);
}

function formatCurrency(value) {
  return `Rs.${value.toFixed(2)}`;
}

function buildLot() {
  const spots = [];
  let id = 1;

  for (const [type, count] of Object.entries(LOT_CONFIG)) {
    for (let i = 0; i < count; i += 1) {
      spots.push({
        id: id++,
        type,
        occupied: false,
        vehicle: null,
      });
    }
  }

  state.spots = spots;
}

function findAvailableSpot(vehicleType) {
  const requiredSpotType = TYPE_TO_SPOT[vehicleType];

  return state.spots.find(
    (spot) => spot.type === requiredSpotType && !spot.occupied
  );
}

function showMessage(text, type = 'success') {
  const messageBox = document.getElementById('message');
  messageBox.textContent = text;
  messageBox.className = `message ${type}`;
}

function renderStats() {
  const total = state.spots.length;
  const occupied = state.spots.filter((spot) => spot.occupied).length;
  const available = total - occupied;
  const occupancyPercentage = total === 0 ? 0 : Math.round((occupied / total) * 100);

  document.getElementById('total-spots').textContent = String(total);
  document.getElementById('available-spots').textContent = String(available);
  document.getElementById('occupied-spots').textContent = String(occupied);
  document.getElementById('occupancy-percent').textContent = `${occupancyPercentage}%`;
  document.getElementById('occupancy-rate-text').textContent = `${occupancyPercentage}%`;
  document.getElementById('occupancy-fill').style.width = `${occupancyPercentage}%`;
  document.getElementById('vehicle-count-chip').textContent = `${occupied} active`;

  const statusText = occupied === 0 ? 'Lot empty' : occupied < total ? 'Lot active' : 'Lot full';
  document.getElementById('lot-status').textContent = statusText;
}

function renderSpots() {
  const spotGrid = document.getElementById('spot-grid');

  spotGrid.innerHTML = state.spots
    .map((spot) => {
      const label = spot.type === 'SMALL' ? 'Bike' : spot.type === 'MEDIUM' ? 'Car' : 'Truck';
      const statusClass = spot.occupied ? 'occupied' : 'free';
      const statusText = spot.occupied ? 'Occupied' : 'Free';
      const vehicleText = spot.vehicle ? spot.vehicle.plate : 'No vehicle';

      return `
        <article class="spot-card ${statusClass}">
          <h3>Spot #${spot.id}</h3>
          <div class="spot-meta">Type: ${label}</div>
          <div class="spot-meta">Vehicle: ${vehicleText}</div>
          <span class="status-badge ${statusClass}">${statusText}</span>
        </article>
      `;
    })
    .join('');
}

function renderTickets() {
  const tableBody = document.getElementById('tickets-table');

  if (state.tickets.length === 0) {
    tableBody.innerHTML = `
      <tr>
        <td colspan="6" class="empty-state">No active vehicles in the lot.</td>
      </tr>
    `;
    return;
  }

  tableBody.innerHTML = state.tickets
    .map((ticket) => {
      const rate = RATE_BY_TYPE[ticket.spotType];
      return `
        <tr>
          <td>#${ticket.id}</td>
          <td>${ticket.vehicle.plate}</td>
          <td>${ticket.vehicle.type}</td>
          <td>#${ticket.spotId}</td>
          <td>${formatTime(ticket.entryTime)}</td>
          <td>${formatCurrency(ticket.fee || 0)} / ${rate}/hr</td>
        </tr>
      `;
    })
    .join('');
}

function renderAll() {
  renderStats();
  renderSpots();
  renderTickets();
}

function parkVehicle(event) {
  event.preventDefault();

  const plateInput = document.getElementById('plate-input');
  const vehicleTypeInput = document.getElementById('vehicle-type');
  const plate = plateInput.value.trim().toUpperCase();
  const vehicleType = vehicleTypeInput.value;

  if (!plate) {
    showMessage('Please enter a valid license plate.', 'error');
    return;
  }

  const existingTicket = state.tickets.find(
    (ticket) => ticket.vehicle.plate === plate
  );

  if (existingTicket) {
    showMessage('This vehicle is already parked in the lot.', 'warning');
    return;
  }

  const spot = findAvailableSpot(vehicleType);
  if (!spot) {
    showMessage(`No available ${vehicleType.toLowerCase()} parking spot.`, 'error');
    return;
  }

  const entryTime = new Date();
  const ticket = {
    id: state.nextTicketId++,
    vehicle: {
      plate,
      type: vehicleType,
    },
    spotId: spot.id,
    spotType: spot.type,
    entryTime,
    fee: 0,
  };

  spot.occupied = true;
  spot.vehicle = ticket.vehicle;
  state.tickets.push(ticket);

  plateInput.value = '';
  showMessage(`Vehicle ${plate} parked successfully in spot #${spot.id}.`);
  renderAll();
}

function buildReceiptText(receipt) {
  return [
    'UrbanFlow Parking Receipt',
    '========================',
    `Receipt #: ${receipt.id}`,
    `Plate: ${receipt.plate}`,
    `Vehicle Type: ${receipt.vehicleType}`,
    `Spot: #${receipt.spotId}`,
    `Entry: ${formatTime(receipt.entryTime)}`,
    `Exit: ${formatTime(receipt.exitTime)}`,
    `Hours: ${receipt.hours}`,
    `Total: ${formatCurrency(receipt.total)}`,
    '',
    'Thank you for parking with us.'
  ].join('\n');
}

function showReceipt(receipt) {
  state.lastReceipt = receipt;
  const receiptBox = document.getElementById('receipt-box');
  const receiptId = document.getElementById('receipt-id');
  const receiptSummary = document.getElementById('receipt-summary');

  receiptId.textContent = `#${receipt.id}`;
  receiptSummary.innerHTML = `
    <strong>${receipt.plate}</strong><br>
    Vehicle: ${receipt.vehicleType}<br>
    Spot: #${receipt.spotId}<br>
    Entry: ${formatTime(receipt.entryTime)}<br>
    Exit: ${formatTime(receipt.exitTime)}<br>
    Duration: ${receipt.hours} hour(s)<br>
    Total: <strong>${formatCurrency(receipt.total)}</strong>
  `;

  receiptBox.classList.remove('hidden');
}

function printReceipt() {
  if (!state.lastReceipt) {
    return;
  }

  const content = buildReceiptText(state.lastReceipt);
  const printWindow = window.open('', '_blank', 'width=600,height=700');
  printWindow.document.write(`
    <html>
      <head><title>Parking Receipt</title></head>
      <body style="font-family: Arial; padding: 24px; line-height: 1.7;">
        <pre>${content.replace(/</g, '&lt;')}</pre>
      </body>
    </html>
  `);
  printWindow.document.close();
  printWindow.focus();
  printWindow.print();
}

function downloadReceipt() {
  if (!state.lastReceipt) {
    return;
  }

  const text = buildReceiptText(state.lastReceipt);
  const blob = new Blob([text], { type: 'text/plain;charset=utf-8' });
  const url = URL.createObjectURL(blob);
  const link = document.createElement('a');
  link.href = url;
  link.download = `parking-receipt-${state.lastReceipt.id}.txt`;
  link.click();
  URL.revokeObjectURL(url);
}

function emailReceipt() {
  if (!state.lastReceipt) {
    return;
  }

  const subject = encodeURIComponent(`Parking Receipt #${state.lastReceipt.id}`);
  const body = encodeURIComponent(buildReceiptText(state.lastReceipt));
  window.location.href = `mailto:?subject=${subject}&body=${body}`;
}

function unparkVehicle(event) {
  event.preventDefault();

  const exitPlate = document.getElementById('exit-plate').value.trim().toUpperCase();
  const ticketIndex = state.tickets.findIndex(
    (ticket) => ticket.vehicle.plate === exitPlate
  );

  if (ticketIndex === -1) {
    showMessage('No parked vehicle matches that license plate.', 'error');
    return;
  }

  const ticket = state.tickets[ticketIndex];
  const exitTime = new Date();
  const elapsedMs = Math.max(exitTime - new Date(ticket.entryTime), 0);
  const elapsedHours = Math.ceil(elapsedMs / (1000 * 60 * 60));
  const fee = Math.max(elapsedHours, 1) * RATE_BY_TYPE[ticket.spotType];

  ticket.fee = fee;
  ticket.exitTime = exitTime;

  const spot = state.spots.find((item) => item.id === ticket.spotId);
  if (spot) {
    spot.occupied = false;
    spot.vehicle = null;
  }

  const receipt = {
    id: ticket.id,
    plate: exitPlate,
    vehicleType: ticket.vehicle.type,
    spotId: ticket.spotId,
    entryTime: ticket.entryTime,
    exitTime,
    hours: elapsedHours,
    total: fee,
  };

  state.tickets.splice(ticketIndex, 1);
  document.getElementById('exit-plate').value = '';
  showMessage(`${exitPlate} exited successfully. Total fee: ${formatCurrency(fee)}.`);
  showReceipt(receipt);
  renderAll();
}

document.getElementById('park-form').addEventListener('submit', parkVehicle);
document.getElementById('exit-form').addEventListener('submit', unparkVehicle);
document.getElementById('print-receipt').addEventListener('click', printReceipt);
document.getElementById('download-receipt').addEventListener('click', downloadReceipt);
document.getElementById('email-receipt').addEventListener('click', emailReceipt);

buildLot();
renderAll();
